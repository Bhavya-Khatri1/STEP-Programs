class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 1;
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdraw amount or insufficient balance!");
        }
    }
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("--------------------------");
    }
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", accountCounter++);
    }
}
public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Alice", 5000);
        accounts[1] = new BankAccount("Bob", 3000);
        accounts[2] = new BankAccount("Charlie", 10000);
        accounts[0].deposit(2000);
        accounts[1].withdraw(1000);
        accounts[2].withdraw(15000);
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }
        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
    }
}