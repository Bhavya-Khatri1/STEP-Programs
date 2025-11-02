import java.util.*;

class WindowStats {
    private final int windowMs; // window size in milliseconds
    private final Queue<Event> queue = new LinkedList<>();
    private final Map<String, Integer> userCount = new HashMap<>();

    // Constructor
    public WindowStats(int WSeconds) {
        this.windowMs = WSeconds * 1000;
    }

    // Each event is a (timestamp, userId)
    private static class Event {
        long tMs;
        String userId;
        Event(long tMs, String userId) {
            this.tMs = tMs;
            this.userId = userId;
        }
    }

    // Ingest an event and return stats
    public String ingest(long tMs, String userId) {
        // Add new event
        queue.offer(new Event(tMs, userId));
        userCount.put(userId, userCount.getOrDefault(userId, 0) + 1);

        // Evict old events
        while (!queue.isEmpty() && queue.peek().tMs <= tMs - windowMs) {
            Event old = queue.poll();
            String u = old.userId;
            userCount.put(u, userCount.get(u) - 1);
            if (userCount.get(u) == 0)
                userCount.remove(u);
        }

        // Compute unique users & QPS
        int uniqueUsers = userCount.size();
        int qps = (int) Math.floor((double) queue.size() / (windowMs / 1000));

        return uniqueUsers + " " + qps;
    }

    // Display current queue state
    public void displayQueue() {
        System.out.print("Current Window: [");
        for (Event e : queue) {
            System.out.print("(" + e.tMs + "," + e.userId + ") ");
        }
        System.out.println("]");
    }

    // Main demo
    public static void main(String[] args) {
        WindowStats ws = new WindowStats(5); // 5-second window

        System.out.println(ws.ingest(1000, "A"));  // (1000,A)
        ws.displayQueue();
        System.out.println(ws.ingest(2000, "A"));  // (2000,A)
        ws.displayQueue();
        System.out.println(ws.ingest(2500, "B"));  // (2500,B)
        ws.displayQueue();
        System.out.println(ws.ingest(7000, "B"));  // evict ≤2000
        ws.displayQueue();
    }
}