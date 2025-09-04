public class GameController {

    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;
    public GameController() {
        controllerBrand = "GenericPad";
        connectionType = "USB";
        hasVibration = true;
        batteryLevel = 100;
        sensitivity = 1.0;
    }
    public GameController(String controllerBrand, String connectionType,
                          boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            this.batteryLevel = 100;
        }
        if (sensitivity >= 0.1 && sensitivity <= 3.0) {
            this.sensitivity = sensitivity;
        } else {
            this.sensitivity = 1.0;
        }
    }
    public GameController(String brand, String connectionType) {
        this.controllerBrand = brand;
        this.connectionType = connectionType;
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }
    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }
    public void displayConfiguration() {
        System.out.println("\n--- Controller Configuration ---");
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }
    public void testVibration() {
        if (hasVibration) {
            System.out.println("BUZZ Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }
    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");
        GameController c1 = new GameController();
        GameController c2 = new GameController("ProPad", "Wireless", false, 85, 2.5);
        GameController c3 = new GameController("LitePad", "Bluetooth");
        c1.calibrateController();
        c1.displayConfiguration();
        c1.testVibration();
        c2.calibrateController();
        c2.displayConfiguration();
        c2.testVibration();
        c3.calibrateController();
        c3.displayConfiguration();
        c3.testVibration();
    }
}