public class QueueUsingArray {
    int front, rear, size;
    int[] queue;

    // Constructor to initialize queue
    public QueueUsingArray(int size) {
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
        return rear == size - 1;
    }

    // Enqueue operation (insert element)
    void enqueue(int element) {
        if (isFull()) {
            System.out.println("Queue is Full! Cannot insert " + element);
            return;
        }
        if (isEmpty()) {
            front = 0; // first element
        }
        queue[++rear] = element;
        System.out.println(element + " inserted into the queue.");
    }

    // Dequeue operation (remove element)
    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty! Cannot dequeue.");
            return;
        }
        System.out.println(queue[front] + " removed from the queue.");
        if (front == rear) {
            // Queue becomes empty
            front = rear = -1;
        } else {
            front++;
        }
    }

    // Peek operation (view front element)
    void peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
        } else {
            System.out.println("Front element is: " + queue[front]);
        }
    }

    // Display all elements
    void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return;
        }
        System.out.print("Queue elements: ");
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        QueueUsingArray q = new QueueUsingArray(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();

        q.dequeue();
        q.peek();
        q.display();

        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60); // Should show "Queue is Full"
        q.display();
    }
}