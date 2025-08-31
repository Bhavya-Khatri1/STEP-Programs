import java.util.Scanner;
public class TextCalculator {
    public static int evaluate(String expr) {
        String[] tokens = expr.split(" ");
        int result = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i];
            int num = Integer.parseInt(tokens[i+1]);
            switch(op) {
                case "+": result += num; break;
                case "-": result -= num; break;
                case "*": result *= num; break;
                case "/": if(num!=0) result /= num; break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression (like 10 + 20 * 2):");
        String expr = sc.nextLine();
        int ans = evaluate(expr);
        System.out.println("Result: "+ans);
    }
}