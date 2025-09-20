// Base class
class Color {
    protected String name;

    // Constructor
    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor called");
    }

    public void displayColor() {
        System.out.println("Color Name: " + name);
    }
}

// Intermediate class
class PrimaryColor extends Color {
    protected int intensity; // 0 - 100

    public PrimaryColor(String name, int intensity) {
        super(name); // call Color constructor
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called");
    }

    public void displayPrimaryColor() {
        displayColor(); // call parent method
        System.out.println("Intensity: " + intensity + "%");
    }
}

// Final class
class RedColor extends PrimaryColor {
    private String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity); // call PrimaryColor constructor
        this.shade = shade;
        System.out.println("RedColor constructor called");
    }

    public void displayRedColor() {
        displayPrimaryColor(); // show parent details
        System.out.println("Shade: " + shade);
    }
}

// Testing class
public class ColorTest {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", 90, "Crimson");
        System.out.println();
        red.displayRedColor();
    }
}