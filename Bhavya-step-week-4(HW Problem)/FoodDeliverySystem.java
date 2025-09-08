class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;

    // 1. Default constructor
    FoodOrder() {
        this.customerName = "Unknown";
        this.foodItem = "Unknown";
        this.quantity = 0;
        this.price = 0.0;
    }

    // 2. Constructor with food item
    FoodOrder(String foodItem) {
        this.customerName = "Customer";
        this.foodItem = foodItem;
        this.quantity = 1;
        this.price = 100.0;
    }

    // 3. Constructor with food item and quantity
    FoodOrder(String foodItem, int quantity) {
        this.customerName = "Customer";
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * 120.0; // fixed rate per item
    }

    void printBill() {
        System.out.println("🍔 Order Details:");
        System.out.println("Customer: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: ₹" + price);
        System.out.println("---------------------");
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        FoodOrder o1 = new FoodOrder();
        FoodOrder o2 = new FoodOrder("Burger");
        FoodOrder o3 = new FoodOrder("Pizza", 3);

        o1.printBill();
        o2.printBill();
        o3.printBill();
    }
}