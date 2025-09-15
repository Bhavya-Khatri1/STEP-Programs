import java.util.*;

class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age;
    private int happiness;
    private int health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Child", "Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    // Constructor chaining
    public VirtualPet() {
        this("DefaultPet", new PetSpecies("Dog", DEFAULT_EVOLUTION_STAGES, 15, "Land"), 0, 50, 50);
    }

    public VirtualPet(String name) {
        this(name, new PetSpecies("Cat", DEFAULT_EVOLUTION_STAGES, 20, "House"), 0, 60, 60);
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 0, 70, 70);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        this.petId = UUID.randomUUID().toString();
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = name;
        this.species = species;
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    // Getters/Setters
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public int getAge() { return age; }
    public void setAge(int age) { if (age >= 0) this.age = age; }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness, MAX_HAPPINESS); }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health, MAX_HEALTH); }

    private int validateStat(int value, int max) {
        if (value < 0) return 0;
        if (value > max) return max;
        return value;
    }

    // Public methods
    public void feedPet(String foodType) {
        modifyHealth(10);
        System.out.println(petName + " enjoyed " + foodType);
    }

    public void playWithPet(String gameType) {
        modifyHappiness(15);
        System.out.println(petName + " played " + gameType);
    }

    // Private methods
    private void modifyHappiness(int change) {
        happiness = validateStat(happiness + change, MAX_HAPPINESS);
    }

    private void modifyHealth(int change) {
        health = validateStat(health + change, MAX_HEALTH);
    }

    @Override
    public String toString() {
        return "Pet: " + petName + " (" + species.getSpeciesName() + "), Age: " + age +
               ", Happiness: " + happiness + ", Health: " + health;
    }
}