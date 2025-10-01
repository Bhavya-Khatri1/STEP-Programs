public class BankTest {
    public static void main(String[] args) {
        // BankAccount reference -> SavingsAccount
        BankAccount acc1 = new SavingsAccount(10000);
        acc1.displayBalance();
        acc1.calculateInterest();

        // BankAccount reference -> CurrentAccount
        BankAccount acc2 = new CurrentAccount(15000);
        acc2.displayBalance();
        acc2.calculateInterest();
    }
}