class SmartDevice {
    protected String name;
    public SmartDevice(String name) { this.name = name; }
    public void start() { System.out.println("Starting " + name); }
    public void stop()  { System.out.println("Stopping " + name); }
}

class Light extends SmartDevice {
    public Light(String name) { super(name); }
    public void setColor(String color) { System.out.println("Setting " + name + " color to " + color); }
    public void dim(int percent) { System.out.println("Dimming " + name + " to " + percent + "%"); }
}

class Thermostat extends SmartDevice {
    public Thermostat(String name) { super(name); }
    public void setTemperature(int celsius) { System.out.println("Setting " + name + " temperature to " + celsius + "°C"); }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice[] devices = new SmartDevice[] {
            new Light("Living Room Light"),
            new Thermostat("Hallway Thermostat")
        };

        for (SmartDevice d : devices) {
            d.start();

            if (d instanceof Light) {
                Light l = (Light) d; // safe because of instanceof
                l.setColor("Warm White");
                l.dim(70);
            } else if (d instanceof Thermostat) {
                Thermostat t = (Thermostat) d;
                t.setTemperature(24);
            }

            d.stop();
        }
    }
}