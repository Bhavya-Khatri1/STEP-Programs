import java.util.*;
public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;
            System.out.println("Character: " + ch + " | ASCII: " + ascii);
            System.out.println("Type: " + classifyCharacter(ch));
            if (Character.isLetter(ch)) {
                char toggled = toggleCase(ch);
                System.out.println("Toggled case: " + toggled + " (ASCII: " + (int) toggled + ")");
                System.out.println("Difference in ASCII values: " + Math.abs(((int) ch) - ((int) toggled)));
            }
            System.out.println("-------------------");
        }
        System.out.println("Caesar Cipher (+3): " + caesarCipher(input, 3));
        scanner.close();
    }
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        else if (Character.isLowerCase(ch)) return "Lowercase Letter";
        else if (Character.isDigit(ch)) return "Digit";
        else return "Special Character";
    }
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) {
            return (char) (ch + 32);
        } else if (Character.isLowerCase(ch)) {
            return (char) (ch - 32);
        } else {
            return ch;
        }
    }
    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            sb.append(ch);
        }
        return sb.toString();
    }
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " : " + (char) i);
        }
    }
    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int) text.charAt(i);
        }
        return arr;
    }
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}