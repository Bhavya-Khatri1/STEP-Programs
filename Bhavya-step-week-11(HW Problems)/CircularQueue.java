public class CircularQueue {
    int front, rear, size;
    int[] queue;

    // Constructor to initialize the circular queue
    public CircularQueue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    // Check if queue is empty
    boolean isEmpty() {
        return front == -1;
    }

    // Check if queue is full
    boolean isFull() {
        return (front == 0 && rear == size - 1) || (rear + 1) % size == front;
    }

    // Insert element into circular queue
    void enqueue(int element) {
        if (isFull()) {
            System.out.println("Queue is Full! Cannot insert " + element);
            return;
        }

        // First element condition
        if (front == -1) {
            front = 0;
        }

        // Move rear circularly
        rear = (rear + 1) % size;
        queue[rear] = element;
        System.out.println(element + " inserted into the queue.");
    }

    // Delete element from circular queue
    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty! Cannot delete.");
            return;
        }

        System.out.println(queue[front] + " removed from the queue.");

        // Queue has only one element
        if (front == rear) {
            front = rear = -1;
        } else {
            // Move front circularly
            front = (front + 1) % size;
        }
    }

    // Display all elements
    void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return;
        }

        System.out.print("Queue elements: ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    // Main method to test circular queue
    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40);
        cq.display();

        cq.dequeue();
        cq.dequeue();
        cq.display();

        cq.enqueue(50);
        cq.enqueue(60);
        cq.display();

        cq.enqueue(70); // Should show full message
    }
}