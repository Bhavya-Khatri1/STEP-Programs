// Parent class
class Fruit {
    protected String color;   // accessible in child classes
    protected String taste;

    // Constructor
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Method to display fruit details
    public void displayFruitInfo() {
        System.out.println("Fruit Color: " + color);
        System.out.println("Fruit Taste: " + taste);
    }
}

// Child class
class Apple extends Fruit {
    private String variety;

    // Constructor (uses super to initialize parent fields)
    public Apple(String color, String taste, String variety) {
        super(color, taste);   // calls Fruit constructor
        this.variety = variety;
    }

    // Method to display apple details
    public void displayAppleInfo() {
        // Accessing inherited fields directly since they're protected
        System.out.println("Apple Color: " + color);
        System.out.println("Apple Taste: " + taste);
        System.out.println("Apple Variety: " + variety);
    }
}

// Testing class
public class FruitTest {
    public static void main(String[] args) {
        Apple a1 = new Apple("Red", "Sweet", "Fuji");
        a1.displayFruitInfo();   // inherited method
        System.out.println();
        a1.displayAppleInfo();   // apple-specific method
    }
}