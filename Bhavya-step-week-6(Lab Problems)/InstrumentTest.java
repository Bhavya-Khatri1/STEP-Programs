// Base class
class Instrument {
    protected String name;
    protected String material;

    public Instrument(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public void displayInfo() {
        System.out.println("Instrument: " + name);
        System.out.println("Material: " + material);
    }
}

// Child class 1
class Piano extends Instrument {
    private int keys;

    public Piano(String name, String material, int keys) {
        super(name, material);
        this.keys = keys;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Keys: " + keys);
    }
}

// Child class 2
class Guitar extends Instrument {
    private int strings;

    public Guitar(String name, String material, int strings) {
        super(name, material);
        this.strings = strings;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Strings: " + strings);
    }
}

// Child class 3
class Drum extends Instrument {
    private String type; // e.g., bass, snare

    public Drum(String name, String material, String type) {
        super(name, material);
        this.type = type;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Drum Type: " + type);
    }
}

// Testing class
public class InstrumentTest {
    public static void main(String[] args) {
        Instrument[] instruments = {
            new Piano("Piano", "Wood", 88),
            new Guitar("Guitar", "Mahogany", 6),
            new Drum("Drum", "Steel", "Bass Drum")
        };

        for (Instrument i : instruments) {
            System.out.println("---------------");
            i.displayInfo();
        }
    }
}