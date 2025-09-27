// Parent class
class SmartDevice {
    String deviceName;

    SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    void status() {
        System.out.println(deviceName + " status: OK");
    }
}

// Smart Classroom
class SmartClassroom extends SmartDevice {
    SmartClassroom(String deviceName) {
        super(deviceName);
    }

    void controlLights() {
        System.out.println(deviceName + ": Lights adjusted.");
    }

    void controlAC() {
        System.out.println(deviceName + ": AC temperature set.");
    }

    void controlProjector() {
        System.out.println(deviceName + ": Projector turned on.");
    }
}

// Smart Lab
class SmartLab extends SmartDevice {
    SmartLab(String deviceName) {
        super(deviceName);
    }

    void manageEquipment() {
        System.out.println(deviceName + ": Lab equipment ready.");
    }

    void checkSafety() {
        System.out.println(deviceName + ": Safety protocols checked.");
    }
}

// Smart Library
class SmartLibrary extends SmartDevice {
    SmartLibrary(String deviceName) {
        super(deviceName);
    }

    void trackOccupancy() {
        System.out.println(deviceName + ": Occupancy tracked.");
    }

    void checkBooks() {
        System.out.println(deviceName + ": Books availability checked.");
    }
}

// Class to test
public class SmartDeviceTest {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartClassroom("Classroom 101"),
            new SmartLab("Chem Lab"),
            new SmartLibrary("Main Library")
        };

        for (SmartDevice device : devices) {
            device.status();

            // Safe downcasting
            if (device instanceof SmartClassroom) {
                SmartClassroom classroom = (SmartClassroom) device;
                classroom.controlLights();
                classroom.controlAC();
                classroom.controlProjector();
            } else if (device instanceof SmartLab) {
                SmartLab lab = (SmartLab) device;
                lab.manageEquipment();
                lab.checkSafety();
            } else if (device instanceof SmartLibrary) {
                SmartLibrary library = (SmartLibrary) device;
                library.trackOccupancy();
                library.checkBooks();
            }
        }
    }
}