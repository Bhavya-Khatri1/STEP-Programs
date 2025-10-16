class Address implements Cloneable {
    String city;
    String state;

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    // For deep cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow clone
    protected Object cloneShallow() throws CloneNotSupportedException {
        return super.clone(); // copies only references
    }

    // Deep clone
    protected Object cloneDeep() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = (Address) address.clone(); // new Address object
        return cloned;
    }

    @Override
    public String toString() {
        return name + " lives at " + address;
    }
}

public class CloningDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Kanpur", "Uttar Pradesh");
        Person p1 = new Person("Bhavya", addr);

        // Shallow copy
        Person shallowCopy = (Person) p1.cloneShallow();

        // Deep copy
        Person deepCopy = (Person) p1.cloneDeep();

        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);

        // Modify address to see difference
        addr.city = "Lucknow";

        System.out.println("\nAfter changing original address city:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);
    }
}