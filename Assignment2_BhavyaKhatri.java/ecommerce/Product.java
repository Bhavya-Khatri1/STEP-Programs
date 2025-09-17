package ecommerce;

import java.util.ArrayList;
import java.util.List;

final class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public static Product createProduct(String name, double price) {
        return new Product(name, price);
    }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
}

class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) { items.add(product); }
    public List<Product> getItems() { return items; }
}

class Order {
    private Customer customer;
    private List<Product> products;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}

class PaymentProcessor {
    public void processPayment(Order order, double amount) {
        if (amount >= order.calculateTotal()) {
            System.out.println("Payment successful for " + order.calculateTotal());
        } else {
            System.out.println("Insufficient amount!");
        }
    }
}

class ShippingCalculator {
    public double calculateShipping(Order order) {
        return order.calculateTotal() > 1000 ? 0 : 50;
    }
}

class ECommerceSystem {
    public static void main(String[] args) {
        Customer c = new Customer("Alice", "alice@mail.com");
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 500));

        Order order = new Order(c, cart.getItems());
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment(order, 60000);

        ShippingCalculator shipping = new ShippingCalculator();
        System.out.println("Shipping: " + shipping.calculateShipping(order));
    }
}
