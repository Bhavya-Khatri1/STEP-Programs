// Parent class
class Bird {
    public void fly() {
        System.out.println("Bird is flying...");
    }
}

// Child class 1
class Penguin extends Bird {
    @Override
    public void fly() {
        System.out.println("Penguin cannot fly, it swims instead!");
    }
}

// Child class 2
class Eagle extends Bird {
    @Override
    public void fly() {
        System.out.println("Eagle soars high in the sky!");
    }
}

// Testing class
public class BirdTest {
    public static void main(String[] args) {
        // Polymorphism: array of Bird references
        Bird[] birds = { new Bird(), new Penguin(), new Eagle() };

        for (Bird b : birds) {
            b.fly(); // actual method depends on object type
        }
    }
}