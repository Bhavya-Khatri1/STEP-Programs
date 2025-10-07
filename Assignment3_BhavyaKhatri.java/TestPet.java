interface Animal {
    void eat();
}

interface Pet extends Animal {
    void play();
}

class Dog implements Pet {
    @Override
    public void eat() {
        System.out.println("Dog is eating.");
    }

    @Override
    public void play() {
        System.out.println("Dog is playing.");
    }
}

public class TestPet {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();   // from Animal interface
        myDog.play();  // from Pet interface
    }
}