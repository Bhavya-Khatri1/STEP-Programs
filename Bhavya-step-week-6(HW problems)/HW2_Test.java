class Tool {
    private String privateName = "Private Tool";   // only accessible inside Tool
    protected String protectedName = "Protected Tool"; // accessible in subclass
    public String publicName = "Public Tool";      // accessible everywhere

    // Getter for private field
    public String getPrivateName() {
        return privateName;
    }
}

class Hammer extends Tool {
    public void showAccess() {
        // System.out.println(privateName); // ERROR: private field not accessible
        System.out.println("Private field (via getter): " + getPrivateName()); // through getter
        System.out.println("Protected field: " + protectedName); // accessible
        System.out.println("Public field: " + publicName); // accessible
    }
}

public class HW2_Test {
    public static void main(String[] args) {
        Hammer h = new Hammer();
        h.showAccess();

        System.out.println("\n--- Accessing from outside ---");
        // From outside, only public is directly accessible
        // System.out.println(h.privateName);   // ERROR: private not accessible
        // System.out.println(h.protectedName); // ERROR: protected not accessible outside package
        System.out.println("Public field (outside): " + h.publicName); // ✅ works
        System.out.println("Private field (outside via getter): " + h.getPrivateName()); // works
    }
}