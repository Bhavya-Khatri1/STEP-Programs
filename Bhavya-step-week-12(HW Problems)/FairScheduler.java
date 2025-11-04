import java.util.*;
import java.util.stream.*;

/**
 * Fair single-thread scheduler with deadlines & aging (single-file).
 *
 * Complexity: Each slice produces constant-time operations aside from queue ops.
 * Promotions are lazy and only inspect queue fronts, keeping complexity close to O(n log K + m).
 *
 * Usage:
 *   SchedResult res = FairScheduler.schedule(id, arrival, duration, basePri, deadline, q, A, K);
 *
 */
public final class FairScheduler {

    // --- Output data structures ---
    public static final class Slice {
        public final int id;
        public final long start;
        public final long end;
        public Slice(int id, long start, long end) { this.id = id; this.start = start; this.end = end; }
        public String toString() { return "(" + id + "," + start + "," + end + ")"; }
    }

    public static final class SchedResult {
        public final List<Slice> slices; // ordered time slices
        public final long[][] metrics;   // per-job metrics in input order: {CT, TAT, WT, Lateness}
        public final long maxLateness;
        public final double avgWT;
        public final long fairness;      // maxWT - minWT

        public SchedResult(List<Slice> slices, long[][] metrics, long maxLateness, double avgWT, long fairness) {
            this.slices = slices; this.metrics = metrics; this.maxLateness = maxLateness; this.avgWT = avgWT; this.fairness = fairness;
        }
    }

    // --- Internal job representation ---
    private static final class Job {
        final int id;
        final long arrival;
        final long deadline;
        final int basePri;       // 1..K
        final long origDuration;
        long remaining;          // remaining ms
        long servedSoFar;        // total serviced time so far
        int index;               // original input index (to store metrics)

        Job(int id, long arrival, long duration, int basePri, long deadline, int index) {
            this.id = id; this.arrival = arrival; this.origDuration = duration; this.remaining = duration;
            this.basePri = basePri; this.deadline = deadline; this.servedSoFar = 0; this.index = index;
        }
    }

    /**
     * Compute effective priority at time 'cur' for a job, given aging interval A and max K.
     * effectivePriority = min(K, basePri + floor(max(0, (cur - arrival - servedSoFar)) / A))
     */
    private static int effectivePriority(Job j, long cur, long A, int K) {
        if (A <= 0) return Math.min(K, j.basePri); // defense: if A==0 treat no aging
        long waiting = cur - j.arrival - j.servedSoFar;
        if (waiting < 0) waiting = 0;
        long increments = waiting / A;
        long eff = j.basePri + increments;
        if (eff > K) eff = K;
        return (int) eff;
    }

    /**
     * The main scheduler function.
     *
     * @param ids      job ids (int[])
     * @param arrival  arrival times (long[])
     * @param duration durations (long[])
     * @param basePri  base priorities (int[]) values in 1..K
     * @param deadline deadlines (long[])
     * @param q        quantum (long)
     * @param A        aging interval (long)
     * @param K        number of priority levels (int, >=1)
     *
     * @return SchedResult with slices and metrics
     */
    public static SchedResult schedule(int[] ids, long[] arrival, long[] duration, int[] basePri,
                                       long[] deadline, long q, long A, int K) {
        final int n = ids.length;
        if (!(arrival.length == n && duration.length == n && basePri.length == n && deadline.length == n))
            throw new IllegalArgumentException("Input array lengths mismatch");

        // Prepare job objects (arrivals are guaranteed non-decreasing per spec)
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; ++i) {
            jobs[i] = new Job(ids[i], arrival[i], duration[i], basePri[i], deadline[i], i);
        }

        // Ready queues for effective priorities 1..K. We'll use index 1..K (ignore 0).
        @SuppressWarnings("unchecked")
        ArrayDeque<Job>[] queues = new ArrayDeque[K + 1];
        for (int i = 1; i <= K; ++i) queues[i] = new ArrayDeque<>();

        // Track completions and metrics
        long[][] metrics = new long[n][4]; // CT, TAT, WT, Lateness
        boolean[] done = new boolean[n];
        int remainingJobs = n;

        List<Slice> slices = new ArrayList<>();

        long time = 0L;
        int arrIdx = 0; // index of next arrival to process (jobs sorted by arrival)
        // Enqueue arrivals at time 0
        while (arrIdx < n && jobs[arrIdx].arrival <= time) {
            Job jj = jobs[arrIdx++];
            int eff = Math.min(K, Math.max(1, jj.basePri)); // base priority sanity
            queues[eff].addLast(jj);
        }

        // Utility: find next arrival time or -1
        final long nextArrivalTime = (arrIdx < n) ? jobs[arrIdx].arrival : Long.MAX_VALUE;

        // Simulation loop
        while (remainingJobs > 0) {
            // If no ready job, jump to next arrival
            boolean anyReady = false;
            for (int p = 1; p <= K; ++p) if (!queues[p].isEmpty()) { anyReady = true; break; }
            if (!anyReady) {
                if (arrIdx < n) {
                    time = Math.max(time, jobs[arrIdx].arrival);
                    while (arrIdx < n && jobs[arrIdx].arrival <= time) {
                        Job jj = jobs[arrIdx++];
                        int eff = Math.min(K, Math.max(1, jj.basePri));
                        queues[eff].addLast(jj);
                    }
                    continue;
                } else {
                    // No arrivals left and no ready jobs -> done (shouldn't happen if remainingJobs>0)
                    break;
                }
            }

            // Select a job from highest effective priority (K down to 1)
            Job selected = null;
            int selectedLevel = -1;
            for (int level = K; level >= 1; --level) {
                ArrayDeque<Job> dq = queues[level];
                while (!dq.isEmpty()) {
                    Job front = dq.peekFirst();
                    int eff = effectivePriority(front, time, A, K);
                    if (eff > level) {
                        // promote to eff level
                        dq.removeFirst();
                        queues[eff].addLast(front);
                        // continue to re-check current level's new front
                        continue;
                    } else if (eff < level) {
                        // front has lower effective priority (should rarely happen). Keep it where it is.
                        break;
                    } else { // eff == level, we can dispatch this
                        selected = dq.removeFirst();
                        selectedLevel = level;
                        break;
                    }
                }
                if (selected != null) break;
            }

            if (selected == null) {
                // No eligible job after promotions — possibly because promotions moved all to higher queues;
                // loop to try again (safe). But to avoid spin, continue to next iteration.
                continue;
            }

            // Dispatch selected job for up to one quantum q
            long sliceTime = Math.min(q, selected.remaining);
            long start = time;
            long end = time + sliceTime;
            slices.add(new Slice(selected.id, start, end));

            // Update job served info
            selected.remaining -= sliceTime;
            selected.servedSoFar += sliceTime;
            time = end;

            // During execution some new arrivals may come in; add them now (arrival <= time)
            while (arrIdx < n && jobs[arrIdx].arrival <= time) {
                Job jj = jobs[arrIdx++];
                int eff = Math.min(K, Math.max(1, jj.basePri));
                queues[eff].addLast(jj);
            }

            if (selected.remaining == 0) {
                // job completed at 'time'
                int idx = selected.index;
                metrics[idx][0] = time; // CT
                metrics[idx][1] = time - selected.arrival; // TAT
                metrics[idx][2] = metrics[idx][1] - selected.origDuration; // WT
                metrics[idx][3] = Math.max(0L, time - selected.deadline); // Lateness
                done[selected.index] = true;
                remainingJobs--;
            } else {
                // job still has remaining work; re-compute effective priority at current time and enqueue at tail
                int newEff = effectivePriority(selected, time, A, K);
                queues[newEff].addLast(selected);
            }
        }

        // Compute global metrics
        long maxLateness = 0L;
        long sumWT = 0L;
        long maxWT = Long.MIN_VALUE;
        long minWT = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            long wt = metrics[i][2];
            sumWT += wt;
            if (wt > maxWT) maxWT = wt;
            if (wt < minWT) minWT = wt;
            if (metrics[i][3] > maxLateness) maxLateness = metrics[i][3];
        }
        double avgWT = n > 0 ? (double) sumWT / n : 0.0;
        long fairness = (n > 0) ? (maxWT - minWT) : 0L;

        return new SchedResult(slices, metrics, maxLateness, avgWT, fairness);
    }

    // --- Demo main (mini example) ---
    public static void main(String[] args) {
        // Mini example from prompt
        long q = 2, A = 3;
        int K = 3;

        int[] ids = new int[]{1,2,3};
        long[] arrival = new long[]{0,2,4};
        long[] duration = new long[]{5,4,2};
        int[] basePri = new int[]{1,1,1};
        long[] deadline = new long[]{10,12,11};

        SchedResult res = schedule(ids, arrival, duration, basePri, deadline, q, A, K);

        System.out.println("Slices (id,start,end):");
        for (Slice s : res.slices) System.out.println(s);

        System.out.println("\nPer-job metrics (CT,TAT,WT,Lateness) in input order:");
        for (int i = 0; i < ids.length; ++i) {
            System.out.printf("Job %d: CT=%d, TAT=%d, WT=%d, Lateness=%d%n",
                ids[i], res.metrics[i][0], res.metrics[i][1], res.metrics[i][2], res.metrics[i][3]);
        }

        System.out.printf("%nGlobal: maxLateness=%d, avgWT=%.3f, fairness=%d%n",
            res.maxLateness, res.avgWT, res.fairness);
    }
}