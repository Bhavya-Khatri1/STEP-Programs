class Product {
    int productId;
    String productName;

    // Constructor
    Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    // Override equals() to compare based on productId
    @Override
    public boolean equals(Object obj) {
        if (this == obj) // same reference
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Product p = (Product) obj;
        return this.productId == p.productId;
    }
}

public class ProductComparison {
    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop");
        Product p2 = new Product(101, "Laptop");
        Product p3 = p1; // same reference

        System.out.println("p1 == p2 : " + (p1 == p2));          // false (different objects)
        System.out.println("p1.equals(p2) : " + p1.equals(p2));  // true (same productId)
        System.out.println("p1 == p3 : " + (p1 == p3));          // true (same reference)
        System.out.println("p1.equals(p3) : " + p1.equals(p3));  // true (same object)
    }
}