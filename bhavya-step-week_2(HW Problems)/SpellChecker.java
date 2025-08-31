import java.util.*;
public class SpellChecker {
    public static int stringDistance(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int diff = Math.abs(len1 - len2);
        int min = Math.min(len1, len2);
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff;
    }
    public static String suggestWord(String word, String[] dict) {
        String best = word;
        int minDist = Integer.MAX_VALUE;
        for (String d : dict) {
            int dist = stringDistance(word, d);
            if (dist < minDist) {
                minDist = dist;
                best = d;
            }
        }
        return (minDist <= 2) ? best : word;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"java", "string", "program", "performance", "lab", "practice"};
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();
        String[] words = sentence.split(" ");
        System.out.println("\nWord\tSuggestion");
        for (String w : words) {
            String suggestion = suggestWord(w, dictionary);
            if (!suggestion.equals(w)) {
                System.out.println(w + " -> " + suggestion);
            } else {
                System.out.println(w + " -> Correct");
            }
        }
    }
}