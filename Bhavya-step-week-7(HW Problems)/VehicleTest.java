// Base class
class Vehicle {
    void dispatch() {
        System.out.println("Generic transport dispatch...");
    }
}

// Subclass 1: Bus
class Bus extends Vehicle {
    int capacity;
    
    Bus(int capacity) {
        this.capacity = capacity;
    }
    
    @Override
    void dispatch() {
        System.out.println("Bus dispatched with capacity: " + capacity + " passengers. Following fixed route.\n");
    }
}

// Subclass 2: Taxi
class Taxi extends Vehicle {
    double distance;
    
    Taxi(double distance) {
        this.distance = distance;
    }
    
    @Override
    void dispatch() {
        double fare = distance * 15; // Rs. 15 per km
        System.out.println("Taxi dispatched for " + distance + " km. Fare: Rs." + fare + "\n");
    }
}

// Subclass 3: Train
class Train extends Vehicle {
    int carriages;
    
    Train(int carriages) {
        this.carriages = carriages;
    }
    
    @Override
    void dispatch() {
        System.out.println("Train dispatched with " + carriages + " carriages. Running on schedule.\n");
    }
}

// Subclass 4: Bike
class Bike extends Vehicle {
    String type;
    
    Bike(String type) {
        this.type = type;
    }
    
    @Override
    void dispatch() {
        System.out.println("Bike dispatched: " + type + " - Eco-friendly short trip.\n");
    }
}

// Driver class
public class VehicleTest {
    public static void main(String[] args) {
        // Upcasting all to Vehicle
        Vehicle v1 = new Bus(50);
        Vehicle v2 = new Taxi(12.5);
        Vehicle v3 = new Train(10);
        Vehicle v4 = new Bike("Electric");

        // Dynamic method dispatch in action
        v1.dispatch();
        v2.dispatch();
        v3.dispatch();
        v4.dispatch();
    }
}