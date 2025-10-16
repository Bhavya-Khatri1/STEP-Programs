interface Discount {
    double apply(double amount);
}

class Payment {
    void processTransaction(double amount) {
        // Local Inner Class
        class Validator {
            boolean isValid() {
                return amount > 0;
            }
        }

        Validator validator = new Validator();
        if (!validator.isValid()) {
            System.out.println("Invalid payment amount: " + amount);
            return;
        }

        System.out.println("Payment amount is valid: " + amount);

        // Anonymous Inner Class implementing Discount
        Discount discount = new Discount() {
            @Override
            public double apply(double amt) {
                return amt * 0.9; // 10% discount
            }
        };

        double finalAmount = discount.apply(amount);
        System.out.println("Final amount after discount: " + finalAmount);
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.processTransaction(5000);   // valid payment
        payment.processTransaction(-100);   // invalid payment
    }
}