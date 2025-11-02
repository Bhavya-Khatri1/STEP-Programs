import java.util.*;

class StackList {
    private ArrayList<Integer> stack = new ArrayList<>();

    // Push operation
    void push(int value) {
        stack.add(value);
        System.out.println(value + " pushed to stack");
    }

    // Pop operation
    void pop() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        int removed = stack.remove(stack.size() - 1);
        System.out.println(removed + " popped from stack");
    }

    // Peek operation
    void peek() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Top element: " + stack.get(stack.size() - 1));
    }

    // Check if stack is empty
    boolean isEmpty() {
        return stack.isEmpty();
    }

    // Display stack
    void display() {
        System.out.println("Stack elements: " + stack);
    }
}

public class StackUsingList {
    public static void main(String[] args) {
        StackList s = new StackList();
        s.push(10);
        s.push(20);
        s.push(30);
        s.display();
        s.peek();
        s.pop();
        s.display();
        System.out.println("Is stack empty? " + s.isEmpty());
    }
}