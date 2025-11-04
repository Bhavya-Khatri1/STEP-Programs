import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CustomerServiceQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Using Java's built-in Queue interface with LinkedList
        Queue<String> customerQueue = new LinkedList<>();

        System.out.println("=== Customer Service Simulation ===");
        System.out.println("Options:");
        System.out.println("1. Enqueue Customer");
        System.out.println("2. Dequeue Customer (Serve)");
        System.out.println("3. Display Queue");
        System.out.println("4. Exit");

        while (true) {
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    customerQueue.add(name);
                    System.out.println(name + " added to the queue.");
                    break;

                case 2:
                    if (customerQueue.isEmpty()) {
                        System.out.println("No customers to serve!");
                    } else {
                        String served = customerQueue.remove();
                        System.out.println(served + " has been served.");
                    }
                    break;

                case 3:
                    if (customerQueue.isEmpty()) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Current Queue: " + customerQueue);
                    }
                    break;

                case 4:
                    System.out.println("Simulation ended. Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}