// Abstract class Animal
abstract class Animal {
    protected String name;
    protected String habitat;

    public Animal(String name, String habitat) {
        this.name = name;
        this.habitat = habitat;
    }

    public abstract void eat();
}

// Interface Soundable
interface Soundable {
    void makeSound();
}

// Dog class extending Animal and implementing Soundable
class Dog extends Animal implements Soundable {

    public Dog(String name, String habitat) {
        super(name, habitat);
    }

    @Override
    public void eat() {
        System.out.println(name + " eats meat and dog food.");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks loudly: Woof! Woof!");
    }
}

// Main class
public class LabProblem5 {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", "Domestic");
        dog.eat();
        dog.makeSound();
    }
}