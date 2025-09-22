// Abstract parent
abstract class Food {
    // Template method
    public final void prepare() {
        wash();
        cook();
        serve();
        System.out.println("--- Preparation Complete ---\n");
    }

    // Steps (default or abstract)
    protected void wash() {
        System.out.println("Washing ingredients...");
    }

    protected abstract void cook();   // must be defined by child
    protected abstract void serve();  // must be defined by child
}

// Child 1
class Pizza extends Food {
    @Override
    protected void cook() {
        System.out.println("Baking pizza in oven with toppings.");
    }

    @Override
    protected void serve() {
        System.out.println("Cutting pizza into slices and serving hot.");
    }
}

// Child 2
class Soup extends Food {
    @Override
    protected void cook() {
        System.out.println("Boiling vegetables with spices to make soup.");
    }

    @Override
    protected void serve() {
        System.out.println("Pouring soup into a bowl and serving warm.");
    }
}

public class HW4_Test {
    public static void main(String[] args) {
        System.out.println("--- Preparing Pizza ---");
        Food pizza = new Pizza();
        pizza.prepare();

        System.out.println("--- Preparing Soup ---");
        Food soup = new Soup();
        soup.prepare();
    }
}