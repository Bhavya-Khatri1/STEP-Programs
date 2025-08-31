import java.util.*;
public class TextFormatter {
    public static void justify(String text, int width) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int count = 0;
        for (String word : words) {
            if (count + word.length() > width) {
                System.out.println(line.toString());
                line = new StringBuilder();
                count = 0;
            }
            line.append(word).append(" ");
            count += word.length() + 1;
        }
        System.out.println(line.toString());
    }
    public static void center(String text, int width) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            for (int i = 0; i < padding; i++) System.out.print(" ");
            System.out.println(line);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.println("Enter line width:");
        int width = sc.nextInt();
        System.out.println("\nJustified text:");
        justify(text, width);
        System.out.println("\nCentered text:");
        center(text,width);
    }
}