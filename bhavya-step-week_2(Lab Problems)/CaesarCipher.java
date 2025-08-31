import java.util.*;
public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char)((ch - 'A' + shift) % 26 + 'A'));
            } else if (ch >= 'a' && ch <= 'z') {
                result.append((char)((ch - 'a' + shift) % 26 + 'a'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("\nOriginal: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}