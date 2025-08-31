import java.util.*;
public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ADVANCED STRING ANALYZER");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();
        performAllComparisons(str1, str2);
        System.out.println("Similarity percentage: " + calculateSimilarity(str1, str2) + "%");
        scanner.close();
    }
    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n--- Comparisons ---");
        System.out.println("Reference equality (==): " + (str1 == str2));
        System.out.println("Content equality (equals): " + str1.equals(str2));
        System.out.println("Case-insensitive equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic comparison: " + str1.compareTo(str2));
        System.out.println("Case-insensitive lexicographic comparison: " + str1.compareToIgnoreCase(str2));
    }
    public static double calculateSimilarity(String str1, String str2) {
        int maxLen = Math.max(str1.length(), str2.length());
        if (maxLen == 0) return 100.0;
        int same = 0;
        int minLen = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLen; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                same++;
            }
        }
        return (same * 100.0) / maxLen;
    }
    public static void analyzeMemoryUsage(String... strings) {
        System.out.println("\nMemory Analysis(approx)");
        for (String s : strings) {
            System.out.println("String: \"" + s + "\" | Length: " + s.length() + " | Approx memory: " + (s.length() * 2) + " bytes");
        }
    }
    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) {
            sb.append(s.trim()).append(" ");
        }
        return sb.toString().trim();
    }
    public static void demonstrateStringIntern() {
        String s1 = new String("Hello").intern();
        String s2 = "Hello";
        System.out.println("\nUsing intern(): " + (s1 == s2));
    }
}