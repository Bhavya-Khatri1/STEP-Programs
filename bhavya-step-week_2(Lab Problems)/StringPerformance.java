import java.util.*;
public class StringPerformance {
    public static long testString(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + "a";
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    public static long testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    public static long testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();
        System.out.println("\nMethod\t\tTime(ms)");
        System.out.println("String\t\t" + testString(n));
        System.out.println("StringBuilder\t" + testStringBuilder(n));
        System.out.println("StringBuffer\t" + testStringBuffer(n));
    }
}