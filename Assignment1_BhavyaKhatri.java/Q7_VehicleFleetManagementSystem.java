// Q7_VehicleFleetManagementSystem

class Vehicle {
    String vehicleId;
    String brand;
    String model;
    int year;
    double mileage;
    String fuelType;
    String currentStatus; // Available, In Service, Assigned

    public Vehicle(String id, String brand, String model, int year, double mileage, String fuel) {
        this.vehicleId = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuel;
        this.currentStatus = "Available";
    }

    public void updateMileage(double extra) {
        this.mileage += extra;
    }

    public void scheduleMaintenance() {
        this.currentStatus = "In Service";
    }

    public void assignDriver() {
        this.currentStatus = "Assigned";
    }
}

class Car extends Vehicle {
    int seatingCapacity;

    public Car(String id, String brand, String model, int year, double mileage, String fuel, int seats) {
        super(id, brand, model, year, mileage, fuel);
        this.seatingCapacity = seats;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    public Bus(String id, String brand, String model, int year, double mileage, String fuel, int seats) {
        super(id, brand, model, year, mileage, fuel);
        this.seatingCapacity = seats;
    }
}

class Truck extends Vehicle {
    double loadCapacity;

    public Truck(String id, String brand, String model, int year, double mileage, String fuel, double load) {
        super(id, brand, model, year, mileage, fuel);
        this.loadCapacity = load;
    }
}

class Driver {
    String driverId;
    String driverName;
    String licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    public Driver(String id, String name, String license) {
        this.driverId = id;
        this.driverName = name;
        this.licenseType = license;
        this.assignedVehicle = null;
        this.totalTrips = 0;
    }

    public void assignVehicle(Vehicle v) {
        this.assignedVehicle = v;
        v.assignDriver();
    }

    public void completeTrip(double distance) {
        if (assignedVehicle != null) {
            assignedVehicle.updateMileage(distance);
            totalTrips++;
            assignedVehicle.currentStatus = "Available";
        }
    }
}

class Fleet {
    static int totalVehicles = 0;
    static double fleetValue = 0.0;
    static String companyName = "Campus Transport";
    static double totalFuelConsumption = 0.0;

    Vehicle[] vehicles;
    Driver[] drivers;
    int vCount;
    int dCount;

    public Fleet(int vSize, int dSize) {
        vehicles = new Vehicle[vSize];
        drivers = new Driver[dSize];
        vCount = 0;
        dCount = 0;
    }

    public void addVehicle(Vehicle v, double value) {
        if (vCount < vehicles.length) {
            vehicles[vCount] = v;
            vCount++;
            totalVehicles++;
            fleetValue += value;
        }
    }

    public void addDriver(Driver d) {
        if (dCount < drivers.length) {
            drivers[dCount] = d;
            dCount++;
        }
    }

    public void getVehiclesByType(String type) {
        System.out.println("\nVehicles of type: " + type);
        for (int i = 0; i < vCount; i++) {
            if (vehicles[i] != null) {
                if ((type.equalsIgnoreCase("Car") && vehicles[i] instanceof Car) ||
                    (type.equalsIgnoreCase("Bus") && vehicles[i] instanceof Bus) ||
                    (type.equalsIgnoreCase("Truck") && vehicles[i] instanceof Truck)) {
                    System.out.println(vehicles[i].vehicleId + " - " + vehicles[i].brand + " " + vehicles[i].model);
                }
            }
        }
    }
}

public class Q7_VehicleFleetManagementSystem {
    public static void main(String[] args) {
        Fleet fleet = new Fleet(10, 5);

        // Add vehicles
        Car c1 = new Car("C1", "Toyota", "Corolla", 2020, 15000, "Petrol", 5);
        Bus b1 = new Bus("B1", "Volvo", "9400", 2019, 80000, "Diesel", 50);
        Truck t1 = new Truck("T1", "Tata", "Ace", 2021, 20000, "Diesel", 5.0);

        fleet.addVehicle(c1, 800000);
        fleet.addVehicle(b1, 2500000);
        fleet.addVehicle(t1, 1200000);

        // Add drivers
        Driver d1 = new Driver("D1", "Alice", "LMV");
        Driver d2 = new Driver("D2", "Bob", "HMV");

        fleet.addDriver(d1);
        fleet.addDriver(d2);

        // Assign driver and complete trip
        d1.assignVehicle(c1);
        d1.completeTrip(120);

        d2.assignVehicle(b1);
        d2.completeTrip(300);

        // Show vehicles by type
        fleet.getVehiclesByType("Car");
        fleet.getVehiclesByType("Bus");

        // Show fleet stats
        System.out.println("\nTotal vehicles: " + Fleet.totalVehicles);
        System.out.println("Fleet value: " + Fleet.fleetValue);
    }
}