// Q2_ShoppingCartSystem

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;

    static int totalProducts = 0;

    // Constructor
    public Product(String id, String name, double price, String cat, int qty) {
        this.productId = id;
        this.productName = name;
        this.price = price;
        this.category = cat;
        this.stockQuantity = qty;
        totalProducts++;
    }
}

class ShoppingCart {
    String cartId;
    String customerName;
    Product[] products;   // store products in cart
    int[] quantities;     // store corresponding quantities
    int itemCount;        // current number of products in cart
    double cartTotal;

    // Constructor
    public ShoppingCart(String id, String name, int size) {
        this.cartId = id;
        this.customerName = name;
        this.products = new Product[size];
        this.quantities = new int[size];
        this.itemCount = 0;
        this.cartTotal = 0.0;
    }

    // Add product to cart
    public void addProduct(Product p, int qty) {
        if (p == null || qty <= 0) return;

        // Check if product already exists in cart
        for (int i = 0; i < itemCount; i++) {
            if (products[i].productId.equals(p.productId)) {
                quantities[i] += qty;
                calculateTotal();
                return;
            }
        }

        // Otherwise, add new product
        if (itemCount < products.length) {
            products[itemCount] = p;
            quantities[itemCount] = qty;
            itemCount++;
            calculateTotal();
        } else {
            System.out.println("Cart is full!");
        }
    }

    // Remove product from cart by ID
    public void removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].productId.equals(productId)) {
                // Shift left to remove
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                products[itemCount - 1] = null;
                quantities[itemCount - 1] = 0;
                itemCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    // Recalculate total
    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].price * quantities[i];
        }
    }

    // Display cart contents
    public void displayCart() {
        System.out.println("\nCart for " + customerName + " (" + cartId + ")");
        if (itemCount == 0) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.println(products[i].productName + " x " + quantities[i] +
                        " = " + (products[i].price * quantities[i]));
            }
        }
        System.out.println("Total: " + cartTotal);
    }

    // Checkout
    public void checkout() {
        System.out.println("\nChecking out. Amount due: " + cartTotal);
        // Reset cart
        for (int i = 0; i < itemCount; i++) {
            products[i] = null;
            quantities[i] = 0;
        }
        itemCount = 0;
        cartTotal = 0.0;
    }
}

public class Q2_ShoppingCartSystem {
    public static void main(String[] args) {
        // Create some products
        Product p1 = new Product("P001", "Earphones", 499.0, "Electronics", 10);
        Product p2 = new Product("P002", "T-Shirt", 299.0, "Clothing", 20);
        Product p3 = new Product("P003", "Chocolate", 49.0, "Food", 50);

        // Create cart with capacity 5
        ShoppingCart cart = new ShoppingCart("C001", "David", 5);

        // Add products
        cart.addProduct(p1, 2);
        cart.addProduct(p2, 1);
        cart.displayCart();

        // Remove a product
        cart.removeProduct("P001");
        cart.displayCart();

        // Checkout
        cart.checkout();
        cart.displayCart();
    }
}