import java.util.Stack;

public class InfixToPostfix {

    // Method to return operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Method to convert infix expression to postfix
    static String infixToPostfix(String exp) {
        // Stack to hold operators
        Stack<Character> stack = new Stack<>();
        // Result string for postfix
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add it directly to result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }

            // If opening bracket, push to stack
            else if (c == '(') {
                stack.push(c);
            }

            // If closing bracket, pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // mismatched brackets
                else
                    stack.pop();
            }

            // If operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(')
                        break;
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Main method
    public static void main(String[] args) {
        String expression = "A+B*C";
        System.out.println("Infix Expression: " + expression);
        System.out.println("Postfix Expression: " + infixToPostfix(expression));
    }
}