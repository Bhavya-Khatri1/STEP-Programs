import java.util.Scanner;
import java.util.Stack;

public class StackUndoFeature {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> textStack = new Stack<>();

        System.out.println("Simple Text Editor (Type 'undo' to revert, 'exit' to quit)");
        System.out.println("----------------------------------------------------------");

        while (true) {
            System.out.print("Enter text: ");
            String input = sc.nextLine();

            // Exit condition
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting editor...");
                break;
            }

            // Undo condition
            else if (input.equalsIgnoreCase("undo")) {
                if (textStack.isEmpty()) {
                    System.out.println("Nothing to undo!");
                } else {
                    String removed = textStack.pop();
                    System.out.println("Undo performed. Removed: \"" + removed + "\"");
                }
            }

            // Otherwise, push the action
            else {
                textStack.push(input);
                System.out.println("\"" + input + "\" added.");
            }

            // Display current state
            System.out.print("Current Text: ");
            if (textStack.isEmpty()) {
                System.out.println("[Empty]");
            } else {
                for (String s : textStack) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
        sc.close();
    }
}