import java.util.*;
class VirtualPet {
    private final String petId; 
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String stage;

    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static int totalPetsCreated = 0;
    public VirtualPet() {
        this("MysteryPet", getRandomSpecies(), 0, 50, 50, EVOLUTION_STAGES[0]);
    }
    public VirtualPet(String name) {
        this(name, getRandomSpecies(), 1, 60, 60, EVOLUTION_STAGES[1]);
    }
    public VirtualPet(String name, String species) {
        this(name, species, 2, 70, 70, EVOLUTION_STAGES[2]);
    }
    public VirtualPet(String name, String species, int age, int happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = name;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stage = stage;
        totalPetsCreated++;
    }
    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Phoenix", "Turtle"};
        return speciesList[new Random().nextInt(speciesList.length)];
    }
    private static String generatePetId() {
        return "PET-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
    public void feedPet() {
        health = Math.min(100, health + 10);
    }
    public void playWithPet() {
        happiness = Math.min(100, happiness + 10);
    }
    public void healPet() {
        health = 100;
    }
    public void simulateDay() {
        age++;
        happiness -= new Random().nextInt(5);
        health -= new Random().nextInt(5);
        evolvePet();
        if (health <= 0) stage = "Ghost 👻";
    }
    private void evolvePet() {
        if (age < EVOLUTION_STAGES.length) {
            stage = EVOLUTION_STAGES[age];
        }
    }
    public void showStatus() {
        System.out.printf("[%s] %s the %s | Age: %d | Happiness: %d | Health: %d | Stage: %s%n",
                petId, petName, species, age, happiness, health, stage);
    }
    public static int getTotalPetsCreated() {
        return totalPetsCreated;
    }
}
public class PetDaycare {
    public static void main(String[] args) {
        VirtualPet p1 = new VirtualPet();
        VirtualPet p2 = new VirtualPet("Fluffy");
        VirtualPet p3 = new VirtualPet("Sparky", "Dog");
        for (int day = 1; day <= 5; day++) {
            System.out.println("\nDay " + day);
            p1.simulateDay();
            p2.simulateDay();
            p3.simulateDay();
            p1.showStatus();
            p2.showStatus();
            p3.showStatus();
        }
        System.out.println("\nTotal Pets Created: " + VirtualPet.getTotalPetsCreated());
    }
}