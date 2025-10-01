public class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * 0.04; // 4% interest
        System.out.println("Savings Account Interest: " + interest);
    }
}