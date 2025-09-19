public class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel; // 1-10
    private String favoriteActivity;

    // Constructor 1: Basic dog
    public Dog() {
        super("Canine", "Domestic", 12, false, "Brown", 60);
        this.breed = "Unknown";
        this.isDomesticated = true;
        this.loyaltyLevel = 5;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating default dog");
    }

    // Constructor 2: Detailed dog
    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod,
               String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Constructor 3: Copy constructor
    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod,
             other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
    }

    // Overridden methods
    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    // Dog-specific methods
    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Dog's loyalty level: " + loyaltyLevel + "/10");
    }

    // Demonstrate inheritance chain
    public void demonstrateInheritance() {
        eat();
        move();
        sleep();
        nurse();
        regulateTemperature();
        System.out.println(getAnimalInfo());
    }

    // Main method
    public static void main(String[] args) {
        // 1. Test constructor chaining
        Dog dog1 = new Dog();
        Dog dog2 = new Dog("Canine", "House", 14, false, "Black", 65,
                           "Labrador", true, 9, "Swimming");

        // 2. Copy constructor
        Dog dog3 = new Dog(dog2);

        // 3. Test methods
        dog2.eat();
        dog2.move();
        dog2.sleep();
        dog2.bark();
        dog2.fetch();
        dog2.showLoyalty();

        // 4. Demonstrate inheritance
        dog2.demonstrateInheritance();

        // 5. IS-A checks
        System.out.println(dog2 instanceof Dog);     // true
        System.out.println(dog2 instanceof Mammal);  // true
        System.out.println(dog2 instanceof Animal);  // true
    }
}