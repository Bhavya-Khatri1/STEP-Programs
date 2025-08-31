public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;
    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }
    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }
    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }
    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Fuel Level: " + fuelLevel);
    }
    public void displayVehicleInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Level: " + fuelLevel);
    }
    static class Car extends Vehicle {
        public Car(String make, String model, int year, double fuelLevel) {
            super(make, model, year, fuelLevel);
        }
    }
    static class Truck extends Vehicle {
        public Truck(String make, String model, int year, double fuelLevel) {
            super(make, model, year, fuelLevel);
        }
    }
    static class Motorcycle extends Vehicle {
        public Motorcycle(String make, String model, int year, double fuelLevel) {
            super(make, model, year, fuelLevel);
        }
    }
    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", "Corolla", 2018, 50);
        Vehicle v2 = new Truck("Volvo", "FH16", 2020, 120);
        Vehicle v3 = new Motorcycle("Yamaha", "R15", 2022, 15);
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();
        Vehicle[] vehicles = {v1, v2, v3};
        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.stopVehicle();
        }

        // Comments:
        // - Reusability: Vehicle code is reused by Car, Truck, Motorcycle.
        // - Extensibility: New vehicle types can be added by extending Vehicle.
        // - Benefit: Avoids writing duplicate code for common behaviors.
    }
}