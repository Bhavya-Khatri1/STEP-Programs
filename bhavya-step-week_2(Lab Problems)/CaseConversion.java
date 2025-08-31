import java.util.*;
public class CaseConversion {
    public static char toUpper(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char)(ch - 32);
        }
        return ch;
    }
    public static char toLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 32);
        }
        return ch;
    }
    public static String toUpperCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toUpper(text.charAt(i)));
        }
        return sb.toString();
    }
    public static String toLowerCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toLower(text.charAt(i)));
        }
        return sb.toString();
    }
    public static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean startWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                sb.append(ch);
                startWord = true;
            } else {
                if (startWord) {
                    sb.append(toUpper(ch));
                    startWord = false;
                } else {
                    sb.append(toLower(ch));
                }
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.println("\nManual Uppercase: " + toUpperCaseManual(text));
        System.out.println("Built-in Uppercase: " + text.toUpperCase());
        System.out.println("Manual Lowercase: " + toLowerCaseManual(text));
        System.out.println("Built-in Lowercase: " + text.toLowerCase());
        System.out.println("Manual Title Case: " + toTitleCase(text));
    }
}