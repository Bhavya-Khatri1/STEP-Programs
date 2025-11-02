import java.util.*;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expr = sc.nextLine();

        if (isBalanced(expr))
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }

    // Function to check if parentheses are balanced
    static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '['))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}