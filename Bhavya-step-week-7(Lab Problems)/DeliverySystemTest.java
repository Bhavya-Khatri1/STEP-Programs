// Food Delivery System using Method Overloading
class DeliverySystem {

    // Basic delivery (just distance)
    void calculateDelivery(double distance) {
        double cost = distance * 5; // example rate
        System.out.println("Basic Delivery: Distance = " + distance + " km, Cost = $" + cost);
    }

    // Premium delivery (distance + priority fee)
    void calculateDelivery(double distance, double priorityFee) {
        double cost = distance * 5 + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance + " km, Priority Fee = $" 
                           + priorityFee + ", Total Cost = $" + cost);
    }

    // Group delivery (distance + number of orders discount)
    void calculateDelivery(double distance, int numberOfOrders) {
        double baseCost = distance * 5;
        double discount = numberOfOrders * 2; // $2 off per order
        double totalCost = baseCost - discount;
        System.out.println("Group Delivery: Distance = " + distance + " km, Orders = " 
                           + numberOfOrders + ", Discount = $" + discount + ", Total Cost = $" + totalCost);
    }

    // Festival special (distance + discount percentage + free delivery threshold)
    void calculateDelivery(double distance, double discountPercentage, double freeDeliveryThreshold) {
        double cost = distance * 5;
        double discountAmount = cost * (discountPercentage / 100);
        double totalCost = cost - discountAmount;
        if (totalCost > freeDeliveryThreshold) {
            totalCost = 0;
            System.out.println("Festival Special: Free delivery applied! Distance = " + distance + " km");
        } else {
            System.out.println("Festival Special: Distance = " + distance + " km, Discount = $" 
                               + discountAmount + ", Total Cost = $" + totalCost);
        }
    }
}

public class DeliverySystemTest {
    public static void main(String[] args) {
        DeliverySystem ds = new DeliverySystem();

        ds.calculateDelivery(10);                     // Basic
        ds.calculateDelivery(10, 15);                 // Premium
        ds.calculateDelivery(10, 3);                  // Group
        ds.calculateDelivery(10, 20, 40);            // Festival Special
    }
}