import java.util.*;
public class TextCompression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = sc.nextLine();
        int[] freq = new int[256]; 
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }
        System.out.println("\nCharacter Frequencies:");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                System.out.println("'" + (char)i + "' : " + freq[i]);
            }
        }
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            compressed.append((int)text.charAt(i)).append(" ");
        }
        System.out.println("\nOriginal: " + text);
        System.out.println("Compressed: " + compressed.toString());
    }
}