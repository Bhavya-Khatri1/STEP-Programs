class Light {
    String type;
    int brightness;

    // Constructor 1
    Light() {
        this("Generic Light", 50);  // chaining to another constructor
        System.out.println("Light(): Default constructor called");
    }

    // Constructor 2
    Light(String type) {
        this(type, 75);  // chaining
        System.out.println("Light(String): Called with type = " + type);
    }

    // Constructor 3
    Light(String type, int brightness) {
        this.type = type;
        this.brightness = brightness;
        System.out.println("Light(String, int): Called with type = " + type + ", brightness = " + brightness);
    }
}

// Child class
class LED extends Light {
    int lifespan;

    // Constructor 1
    LED() {
        this("LED Bulb", 100, 50000); // chaining inside LED
        System.out.println("LED(): Default constructor called");
    }

    // Constructor 2
    LED(String type, int brightness) {
        super(type, brightness); // call parent constructor
        System.out.println("LED(String, int): Called");
    }

    // Constructor 3
    LED(String type, int brightness, int lifespan) {
        super(type, brightness); // call parent
        this.lifespan = lifespan;
        System.out.println("LED(String, int, int): Called with lifespan = " + lifespan);
    }
}

// Test class
public class HW1_Test {
    public static void main(String[] args) {
        System.out.println("\n--- Creating Light with default constructor ---");
        Light l1 = new Light();

        System.out.println("\n--- Creating Light with one-arg constructor ---");
        Light l2 = new Light("Tube Light");

        System.out.println("\n--- Creating LED with default constructor ---");
        LED led1 = new LED();

        System.out.println("\n--- Creating LED with two-arg constructor ---");
        LED led2 = new LED("Panel LED", 150);

        System.out.println("\n--- Creating LED with three-arg constructor ---");
        LED led3 = new LED("Street LED", 200, 100000);
    }
}