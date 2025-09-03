class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int totalRentalDays = 0;
    private static int vehicleCounter = 1;
    public Vehicle(String brand, String model, double rentPerDay) {
        this.vehicleId = generateVehicleId();
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        totalVehicles++;
    }
    public double rentVehicle(int days) {
        if (isAvailable && days > 0) {
            double amount = calculateRent(days);
            isAvailable = false;
            totalRentalDays += days;
            System.out.println(vehicleId + " rented for " + days + " days. Rent = " + amount);
            return amount;
        } else {
            System.out.println(vehicleId + " is not available!");
            return 0;
        }
    }
    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(vehicleId + " has been returned.");
        } else {
            System.out.println(vehicleId + " was not rented.");
        }
    }
    public double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;
        return rent;
    }
    public void displayVehicleInfo() {
        System.out.println("Vehicle ID : " + vehicleId);
        System.out.println("Brand      : " + brand);
        System.out.println("Model      : " + model);
        System.out.println("Rent/Day   : " + rentPerDay);
        System.out.println("Available? : " + isAvailable);
        System.out.println("------------------------------");
    }
    public static void setCompanyName(String name) {
        companyName = name;
    }
    public static double getTotalRevenue() {
        return totalRevenue;
    }
    public static double getAverageRentPerDay() {
        if (totalRentalDays == 0) return 0;
        return totalRevenue / totalRentalDays;
    }
    public static void displayCompanyStats() {
        System.out.println("===== " + companyName + " =====");
        System.out.println("Total Vehicles : " + totalVehicles);
        System.out.println("Total Revenue  : " + totalRevenue);
        System.out.println("Avg Rent/Day   : " + getAverageRentPerDay());
        System.out.println("Total Rental Days: " + totalRentalDays);
        System.out.println("===============================");
    }
    private static String generateVehicleId() {
        return "VEH" + String.format("%03d", vehicleCounter++);
    }
}
public class VehicleRentalDemo {
    public static void main(String[] args) {
        Vehicle.setCompanyName("SuperRide Rentals");
        Vehicle v1 = new Vehicle("Toyota", "Innova", 2000);
        Vehicle v2 = new Vehicle("Honda", "City", 1500);
        Vehicle v3 = new Vehicle("Suzuki", "Swift", 1000);
        v1.displayVehicleInfo();
        v1.rentVehicle(3);
        v2.rentVehicle(5);
        v1.returnVehicle();
        v1.rentVehicle(2);
        Vehicle.displayCompanyStats();
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();
    }
}