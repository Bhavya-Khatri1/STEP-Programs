import java.util.*;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor
    BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = 0;
        this.balance = 0.0;
    }

    // Constructor with name
    BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(900000) + 100000; // 6-digit number
        this.balance = 0.0;
    }

    // Constructor with name and initial balance
    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(900000) + 100000;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: ₹" + amount);
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("---------------------");
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Rahul");
        BankAccount a3 = new BankAccount("Priya", 5000);

        a2.deposit(2000);
        a3.withdraw(1500);

        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();
    }
}