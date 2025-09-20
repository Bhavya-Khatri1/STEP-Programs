// Parent class
class Phone {
    protected String brand;
    protected String model;

    // Default constructor
    public Phone() {
        System.out.println("Phone: Default constructor called");
    }

    // Parameterized constructor
    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone: Parameterized constructor called");
    }
}

// Child class
class SmartPhone extends Phone {
    private String operatingSystem;

    // Default constructor
    public SmartPhone() {
        super(); // calls Phone() default constructor
        System.out.println("SmartPhone: Default constructor called");
    }

    // Parameterized constructor
    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model); // calls parent parameterized constructor
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone: Parameterized constructor called");
    }

    // Method to display details
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Operating System: " + operatingSystem);
    }
}

// Testing class
public class PhoneTest {
    public static void main(String[] args) {
        System.out.println("---- Creating first SmartPhone with default constructor ----");
        SmartPhone s1 = new SmartPhone();

        System.out.println("\n---- Creating second SmartPhone with parameterized constructor ----");
        SmartPhone s2 = new SmartPhone("Samsung", "Galaxy S24", "Android");
        s2.displayInfo();
    }
}