public class PaymentTest {
    public static void main(String[] args) {
        // PaymentGateway reference -> CreditCardPayment
        PaymentGateway p1 = new CreditCardPayment();
        p1.pay(1200);
        p1.refund(500);

        // PaymentGateway reference -> UPIPayment
        PaymentGateway p2 = new UPIPayment();
        p2.pay(800);
        p2.refund(300);
    }
}