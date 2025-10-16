class Car {
    String brand;
    String model;
    double price;

    // Constructor
    Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // Override toString() for readable output
    @Override
    public String toString() {
        return "Car [Brand: " + brand + ", Model: " + model + ", Price: ₹" + price + "]";
    }
}

public class CarInfo {
    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Supra", 8500000);

        // Printing object directly calls toString()
        System.out.println("Car Details: " + c1);

        // Printing class name using getClass()
        System.out.println("Class Name: " + c1.getClass().getName());
    }
}