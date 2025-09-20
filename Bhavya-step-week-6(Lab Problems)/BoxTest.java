// Parent class
class Box {
    public void pack() {
        System.out.println("Box is being packed...");
    }

    public void unpack() {
        System.out.println("Box is being unpacked...");
    }
}

// Child class
class GiftBox extends Box {
    @Override
    public void pack() {
        super.pack(); // call parent method first
        System.out.println("Adding ribbons and decorations to the gift box!");
    }

    @Override
    public void unpack() {
        super.unpack(); // call parent method first
        System.out.println("Unwrapping gift papers and surprises!");
    }
}

// Testing class
public class BoxTest {
    public static void main(String[] args) {
        GiftBox gift = new GiftBox();

        System.out.println("---- Packing Process ----");
        gift.pack();

        System.out.println("\n---- Unpacking Process ----");
        gift.unpack();
    }
}