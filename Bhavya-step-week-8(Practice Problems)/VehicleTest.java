public class VehicleTest {
    public static void main(String[] args) {
        // Vehicle reference pointing to Car
        Vehicle v1 = new Car();
        v1.start();
        v1.fuelType();

        // Vehicle reference pointing to Bike
        Vehicle v2 = new Bike();
        v2.start();
        v2.fuelType();
    }
}