import java.util.*;

public class InfixEvaluator {

    // Operator precedence map
    private static final Map<String, Integer> PRECEDENCE = Map.of(
        "+", 1, "-", 1,
        "*", 2, "/", 2, "%", 2,
        "^", 3
    );

    public static int evaluate(String expr, Map<String, Integer> env) {
        try {
            List<String> rpn = infixToPostfix(expr, env);
            return evalPostfix(rpn);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new RuntimeException("ERROR");
        }
    }

    // Convert infix expression to postfix (RPN)
    private static List<String> infixToPostfix(String expr, Map<String, Integer> env) {
        List<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();

        StringTokenizer st = new StringTokenizer(expr, "+-*/%^(), ", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token)) {
                output.add(token);
            } 
            else if (isVariable(token)) {
                if (!env.containsKey(token))
                    throw new RuntimeException("Missing variable: " + token);
                output.add(String.valueOf(env.get(token)));
            } 
            else if (token.equals("(")) {
                ops.push(token);
            } 
            else if (token.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("("))
                    output.add(ops.pop());
                if (ops.isEmpty()) throw new RuntimeException("Mismatched parentheses");
                ops.pop(); // remove '('
                if (!ops.isEmpty() && isFunction(ops.peek()))
                    output.add(ops.pop());
            } 
            else if (isFunction(token)) {
                ops.push(token);
            } 
            else if (isOperator(token)) {
                while (!ops.isEmpty() && isOperator(ops.peek()) &&
                        precedence(ops.peek()) >= precedence(token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            } 
            else {
                throw new RuntimeException("Invalid token: " + token);
            }
        }

        while (!ops.isEmpty()) {
            if (ops.peek().equals("(") || ops.peek().equals(")"))
                throw new RuntimeException("Mismatched parentheses");
            output.add(ops.pop());
        }

        System.out.println("Postfix (RPN): " + output);
        return output;
    }

    // Evaluate postfix expression
    private static int evalPostfix(List<String> rpn) {
        Stack<Integer> stack = new Stack<>();
        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } 
            else if (isOperator(token)) {
                if (stack.size() < 2) throw new RuntimeException("Malformed expression");
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOperator(a, b, token));
            } 
            else if (isFunction(token)) {
                if (token.equals("abs")) {
                    if (stack.isEmpty()) throw new RuntimeException("Missing operand for abs");
                    stack.push(Math.abs(stack.pop()));
                } 
                else if (token.equals("min") || token.equals("max")) {
                    if (stack.size() < 2) throw new RuntimeException("Missing operands for " + token);
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(token.equals("min") ? Math.min(a, b) : Math.max(a, b));
                }
            } 
            else throw new RuntimeException("Invalid token in RPN: " + token);
        }

        if (stack.size() != 1)
            throw new RuntimeException("Malformed postfix expression");
        return stack.pop();
    }

    // Helper functions
    private static boolean isOperator(String s) { return PRECEDENCE.containsKey(s); }
    private static boolean isFunction(String s) { return s.equals("min") || s.equals("max") || s.equals("abs"); }
    private static boolean isVariable(String s) { return s.matches("[a-zA-Z]+"); }
    private static boolean isNumber(String s) { return s.matches("-?\\d+"); }
    private static int precedence(String s) { return PRECEDENCE.getOrDefault(s, 0); }

    private static int applyOperator(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new RuntimeException("Divide by zero");
                return a / b;
            case "%": return a % b;
            case "^": return (int)Math.pow(a, b);
            default: throw new RuntimeException("Unknown operator: " + op);
        }
    }

    // Demo main
    public static void main(String[] args) {
        Map<String, Integer> env1 = Map.of();
        System.out.println("Result: " + evaluate("3 + 4 * 2 / (1 - 5) ^ 2 ^ 3", env1));

        Map<String, Integer> env2 = Map.of();
        System.out.println("Result: " + evaluate("min(10, max(2, 3*4))", env2));

        Map<String, Integer> env3 = new HashMap<>();
        env3.put("x", -2);
        env3.put("y", -7);
        System.out.println("Result: " + evaluate("-(x) + abs(y)", env3));

        Map<String, Integer> env4 = Map.of("a", 1);
        try {
            System.out.println("Result: " + evaluate("a + b", env4));
        } catch (Exception e) {
            System.out.println("Result: ERROR");
        }
    }
}