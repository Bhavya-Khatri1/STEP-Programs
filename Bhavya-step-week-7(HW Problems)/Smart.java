// Base class
class SmartDevice {
    String name;
    
    SmartDevice(String name) {
        this.name = name;
    }
    
    void showStatus() {
        System.out.println("Smart Device: " + name);
    }
}

// Subclass 1: Smart TV
class SmartTV extends SmartDevice {
    SmartTV(String name) {
        super(name);
    }
    
    void changeChannel(int channel) {
        System.out.println(name + " is set to channel " + channel);
    }
}

// Subclass 2: Smart Thermostat
class SmartThermostat extends SmartDevice {
    SmartThermostat(String name) {
        super(name);
    }
    
    void setTemperature(int temp) {
        System.out.println(name + " temperature set to " + temp + "°C");
    }
}

// Subclass 3: Smart Security System
class SmartSecurity extends SmartDevice {
    SmartSecurity(String name) {
        super(name);
    }
    
    void activateAlarm() {
        System.out.println(name + " alarm activated! 🚨");
    }
}

// Subclass 4: Smart Kitchen Appliance
class SmartKitchen extends SmartDevice {
    SmartKitchen(String name) {
        super(name);
    }
    
    void startCooking(String recipe) {
        System.out.println(name + " is cooking " + recipe);
    }
}

// Driver
public class Smart {
    public static void main(String[] args) {
        // Mixed collection of devices
        SmartDevice[] devices = {
            new SmartTV("Living Room TV"),
            new SmartThermostat("Bedroom Thermostat"),
            new SmartSecurity("Home Security"),
            new SmartKitchen("Smart Oven")
        };
        
        // Safe downcasting using instanceof
        for (SmartDevice device : devices) {
            device.showStatus();
            
            if (device instanceof SmartTV) {
                ((SmartTV)device).changeChannel(5);
            } 
            else if (device instanceof SmartThermostat) {
                ((SmartThermostat)device).setTemperature(22);
            } 
            else if (device instanceof SmartSecurity) {
                ((SmartSecurity)device).activateAlarm();
            } 
            else if (device instanceof SmartKitchen) {
                ((SmartKitchen)device).startCooking("Pizza");
            }
            
            System.out.println();
        }
    }
}