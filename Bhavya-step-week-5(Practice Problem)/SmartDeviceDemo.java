import java.time.LocalDateTime;
import java.util.*;

class SmartDevice {
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    private String deviceName;
    private boolean enabled;

    private int hashedKey;
    private int hashedPassword;

    private final LocalDateTime startupTime;

    public SmartDevice(String deviceName) {
        this.deviceId = UUID.randomUUID().toString();
        this.manufacturingDate = LocalDateTime.now();
        this.serialNumber = "SN" + System.currentTimeMillis();
        this.deviceName = deviceName;
        this.startupTime = LocalDateTime.now();
    }

    // Read-only
    public String getDeviceId() { return deviceId; }
    public String getSerialNumber() { return serialNumber; }
    public LocalDateTime getManufacturingDate() { return manufacturingDate; }

    // Write-only
    public void setEncryptionKey(String key) {
        this.hashedKey = key.hashCode();
    }
    public void setAdminPassword(String pass) {
        this.hashedPassword = pass.hashCode();
    }

    // Read-write
    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String name) { this.deviceName = name; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}

public class SmartDeviceDemo {
    public static void main(String[] args) {
        SmartDevice dev = new SmartDevice("MyPhone");
        System.out.println("Device ID: " + dev.getDeviceId());
        System.out.println("Serial: " + dev.getSerialNumber());

        dev.setEncryptionKey("secretkey");
        dev.setAdminPassword("password123");

        dev.setEnabled(true);
        System.out.println("Enabled: " + dev.isEnabled());
    }
}