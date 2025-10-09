public class Registration {
    public static void main(String[] args) throws CloneNotSupportedException {
        ContactInfo c = new ContactInfo("alice@example.com", "9998887777");
        Student original = new Student("S100", "Alice", c);

        Student shallow = original.clone();
        shallow.contact.phone = "1112223333";
        System.out.println("After shallow clone modification:");
        System.out.println("original: " + original);
        System.out.println("shallow:   " + shallow);

        original.contact.phone = "9998887777";
        Student deep = original.deepClone();
        deep.contact.email = "deep@example.com";
        System.out.println("\nAfter deep clone modification:");
        System.out.println("original: " + original);
        System.out.println("deep:     " + deep);
    }
}