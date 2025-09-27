// Parent class
class Character {
    String name;

    Character(String name) {
        this.name = name;
    }

    // Attack method to be overridden
    void attack() {
        System.out.println(name + " attacks!");
    }
}

// Warrior class
class Warrior extends Character {
    Warrior(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " swings a sword with high defense!");
    }
}

// Mage class
class Mage extends Character {
    Mage(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " casts a powerful spell using mana!");
    }
}

// Archer class
class Archer extends Character {
    Archer(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " shoots an arrow from long range!");
    }
}

// Class to test
public class CharacterTest {
    public static void main(String[] args) {
        // Mixed army of characters
        Character[] army = {
            new Warrior("Thor"),
            new Mage("Gandalf"),
            new Archer("Legolas")
        };

        // Same attack command, different behaviors
        for (Character c : army) {
            c.attack();  // Dynamic method dispatch happens here
        }
    }
}