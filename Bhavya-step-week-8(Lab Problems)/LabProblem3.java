// Abstract class Vehicle
abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    public Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public abstract void startEngine();
}

// Interface Maintainable
interface Maintainable {
    void serviceInfo();
}

// Car class extending Vehicle and implementing Maintainable
class Car extends Vehicle implements Maintainable {
    private String brand;

    public Car(int speed, String fuelType, String brand) {
        super(speed, fuelType);
        this.brand = brand;
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " car engine started. Speed: " + speed + " km/h, Fuel: " + fuelType);
    }

    @Override
    public void serviceInfo() {
        System.out.println(brand + " car requires servicing every 10,000 km.");
    }
}

// Main class
public class LabProblem3 {
    public static void main(String[] args) {
        Car car = new Car(120, "Petrol", "Honda");
        car.startEngine();
        car.serviceInfo();
    }
}