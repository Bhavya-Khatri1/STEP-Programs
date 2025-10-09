public class PaymentGateway {
    public static void main(String[] args) {
        Payment[] payments = {
            new CreditCardPayment(),
            new WalletPayment()
        };

        for (Payment p : payments) {
            System.out.println("Class: " + p.getClass().getSimpleName());
            p.pay();
        }
    }
}