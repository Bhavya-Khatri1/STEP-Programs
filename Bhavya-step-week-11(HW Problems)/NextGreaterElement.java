import java.util.Stack;

public class NextGreaterElement {

    // Method to print the next greater elements
    static void printNextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n]; // array to store answers
        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];

            // Pop smaller elements from stack
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }

            // If stack empty → no greater element
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            // Push current element into stack
            stack.push(current);
        }

        // Print results
        System.out.print("Next Greater Elements: ");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25};
        System.out.print("Input Array: ");
        for (int i : arr) System.out.print(i + " ");
        System.out.println();

        printNextGreaterElements(arr);
    }
}