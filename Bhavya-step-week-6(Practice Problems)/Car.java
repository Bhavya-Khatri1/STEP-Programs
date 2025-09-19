public class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    // Default constructor
    public Car() {
        super(); // Call Vehicle’s default constructor
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Call Vehicle’s parameterized constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    // Overridden methods
    @Override
    public void start() {
        super.start(); // Call Vehicle’s start
        System.out.println("Car specific startup sequence");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs(); // Show Vehicle specs
        System.out.println("Car Specs -> Doors: " + numberOfDoors +
                ", Fuel: " + fuelType + ", Transmission: " + transmissionType);
    }

    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    // Main method for testing
    public static void main(String[] args) {
        // 1. Constructor chaining
        Car car1 = new Car();
        Car car2 = new Car("Toyota", "Corolla", 2021, "Hybrid", 4, "Petrol", "Automatic");

        // 2. Test inheritance
        car2.start();
        car2.displaySpecs();
        System.out.println(car2.getVehicleInfo());

        // 3. Car-specific methods
        car2.openTrunk();
        car2.playRadio();

        // 4. Super keyword usage & polymorphism
        Vehicle v = new Car("Honda", "Civic", 2022, "Petrol", 4, "Diesel", "Manual");
        v.start(); // Will call overridden Car’s start
        v.displaySpecs();
    }
}