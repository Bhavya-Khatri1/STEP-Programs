class Animal {
    // Base class
}

class Dog extends Animal {
    // Dog inherits from Animal
}

class Cat extends Animal {
    // Cat inherits from Animal
}
public class TestInstanceof {
    public static void main(String[] args) {
        Animal[] animals = { new Dog(), new Cat(), new Dog(), new Animal() };
        int dogCount = 0;
        int catCount = 0;
        for (Animal a : animals) {
            if (a instanceof Dog) {
                dogCount++;
            } else if (a instanceof Cat) {
                catCount++;
            }
        }     
        System.out.println("Number of Dogs: " + dogCount);
        System.out.println("Number of Cats: " + catCount);
    }
}