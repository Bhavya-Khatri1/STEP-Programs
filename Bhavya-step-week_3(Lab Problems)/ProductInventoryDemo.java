class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;

    private static int totalProducts = 0;
    private static double totalInventoryValue = 0;
    private static int lowStockCount = 0;
    private static int productCounter = 1;
    public Product(String productName, double price, int quantity, String supplierName, String category) {
        this.productId = generateProductId();
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.category = category;
        totalProducts++;
        totalInventoryValue += calculateProductValue();
        if (isLowStock()) lowStockCount++;
    }
    public void addStock(int qty) {
        if (qty > 0) {
            quantity += qty;
            totalInventoryValue += qty * price;
        }
    }
    public void reduceStock(int qty) {
        if (qty > 0 && qty <= quantity) {
            quantity -= qty;
            totalInventoryValue -= qty * price;
            if (isLowStock()) lowStockCount++;
        } else {
            System.out.println("Invalid stock reduction for " + productName);
        }
    }
    public boolean isLowStock() {
        return quantity < 10;
    }
    public double calculateProductValue() {
        return price * quantity;
    }
    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            totalInventoryValue -= calculateProductValue();
            price = newPrice;
            totalInventoryValue += calculateProductValue();
        }
    }
    public void displayProductInfo() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Name         : " + productName);
        System.out.println("Price        : " + price);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Supplier     : " + supplierName);
        System.out.println("Category     : " + category);
        System.out.println("Total Value  : " + calculateProductValue());
        System.out.println("Low Stock?   : " + (isLowStock() ? "Yes" : "No"));
        System.out.println("--------------------------------");
    }
    public static double calculateTotalInventoryValue(Product[] products) {
        double sum = 0;
        for (Product p : products) {
            if (p != null) sum += p.calculateProductValue();
        }
        return sum;
    }
    public static void findLowStockProducts(Product[] products) {
        System.out.println("=== Low Stock Products (<10 qty) ===");
        for (Product p : products) {
            if (p != null && p.isLowStock()) {
                System.out.println(p.productName + " (" + p.quantity + ")");
            }
        }
    }
    public static void generateInventoryReport(Product[] products) {
        System.out.println("===== INVENTORY REPORT =====");
        System.out.println("Total Products: " + totalProducts);
        System.out.println("Total Value   : " + calculateTotalInventoryValue(products));
        findLowStockProducts(products);
        System.out.println("=============================");
    }
    public static Product searchProduct(Product[] products, String name) {
        for (Product p : products) {
            if (p != null && p.productName.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    private static String generateProductId() {
        return "PROD" + String.format("%03d", productCounter++);
    }
}
public class ProductInventoryDemo {
    public static void main(String[] args) {
        Product[] products = new Product[4];
        products[0] = new Product("Laptop", 50000, 5, "Dell", "Electronics");
        products[1] = new Product("Phone", 30000, 15, "Samsung", "Electronics");
        products[2] = new Product("Table", 2000, 8, "IKEA", "Furniture");
        products[3] = new Product("Chair", 1000, 25, "IKEA", "Furniture");
        for (Product p : products) {
            p.displayProductInfo();
        }
        products[0].addStock(10);
        products[2].reduceStock(5);
        products[1].updatePrice(28000);
        Product found = Product.searchProduct(products, "Laptop");
        if (found != null) {
            System.out.println("Search Result:");
            found.displayProductInfo();
        }
        Product.generateInventoryReport(products);
    }
}