import java.util.*;
public class StringUtilityApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("STRING UTILITY APPLICATION");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Text Analysis");
            System.out.println("2. String Transformations");
            System.out.println("3. ASCII Operations");
            System.out.println("4. Performance Testing");
            System.out.println("5. String Comparison Analysis");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            String text;
            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    text = scanner.nextLine();
                    performTextAnalysis(text);
                    break;

                case 2:
                    System.out.print("Enter text: ");
                    text = scanner.nextLine();
                    System.out.println("Transformations:\n" + performTransformations(text));
                    break;

                case 3:
                    System.out.print("Enter text: ");
                    text = scanner.nextLine();
                    performASCIIOperations(text);
                    break;

                case 4:
                    performPerformanceTest(2000);
                    break;

                case 5:
                    System.out.print("Enter first string: ");
                    String s1 = scanner.nextLine();
                    System.out.print("Enter second string: ");
                    String s2 = scanner.nextLine();
                    performComparisonAnalysis(s1, s2);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    public static void performTextAnalysis(String text) {
        System.out.println("Length: " + text.length());
        String[] words = text.trim().split("\\s+");
        System.out.println("Word count: " + words.length);
        boolean[] visited = new boolean[words.length];
        System.out.println("Word frequency:");
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            int count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equalsIgnoreCase(words[j])) {
                    count++;
                    visited[j] = true;
                }
            }
            System.out.println(words[i] + " : " + count);
        }
    }
    public static String performTransformations(String text) {
        StringBuilder sb = new StringBuilder();
        sb.append("Uppercase: ").append(text.toUpperCase()).append("\n");
        sb.append("Lowercase: ").append(text.toLowerCase()).append("\n");
        sb.append("Trimmed: ").append(text.trim()).append("\n");
        sb.append("Replaced spaces: ").append(text.replace(" ", "_")).append("\n");
        return sb.toString();
    }
    public static void performASCIIOperations(String text) {
        for (char c : text.toCharArray()) {
            System.out.println(c + " : " + (int) c);
        }
        System.out.println("Caesar Cipher (+3): " + caesarCipher(text, 3));
    }
    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char) ((c - base + shift) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static void performPerformanceTest(int n) {
        long start, end;
        start = System.nanoTime();
        String s = "";
        for (int i = 0; i < n; i++)
            s += i;
        end = System.nanoTime();
        System.out.println("String concat: " + (end - start) + " ns");
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(i);
        end = System.nanoTime();
        System.out.println("StringBuilder concat: " + (end - start) + " ns");
        start = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < n; i++)
            sbuf.append(i);
        end = System.nanoTime();
        System.out.println("StringBuffer concat: " + (end - start) + " ns");
    }
    public static void performComparisonAnalysis(String s1, String s2) {
        System.out.println("== operator: " + (s1 == s2));
        System.out.println("equals(): " + s1.equals(s2));
        System.out.println("equalsIgnoreCase(): " + s1.equalsIgnoreCase(s2));
        System.out.println("compareTo(): " + s1.compareTo(s2));
        System.out.println("compareToIgnoreCase(): " + s1.compareToIgnoreCase(s2));
    }
}