import java.util.*;

public class PalindromeStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        if (isPalindrome(str))
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
    }

    static boolean isPalindrome(String str) {
        Stack<Character> stack = new Stack<>();
        String cleanStr = str.replaceAll("\\s+", "").toLowerCase();

        for (char ch : cleanStr.toCharArray()) {
            stack.push(ch);
        }

        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        return cleanStr.equals(reversed);
    }
}