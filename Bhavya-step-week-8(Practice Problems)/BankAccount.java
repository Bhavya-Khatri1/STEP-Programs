public abstract class BankAccount {
    protected double balance;

    // Constructor
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Abstract method
    public abstract void calculateInterest();

    // Non-abstract method
    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}