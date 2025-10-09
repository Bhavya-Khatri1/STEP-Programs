public class Payment {
    public void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Processing credit card payment");
    }
}

class WalletPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Processing wallet payment");
    }
}