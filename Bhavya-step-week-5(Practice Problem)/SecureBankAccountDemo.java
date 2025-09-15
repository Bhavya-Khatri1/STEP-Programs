class SecureBankAccount {
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // default, must be set
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (!isLocked) {
            return balance;
        }
        return -1; // indicates locked
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    public void setPin(int newPin) {
        this.pin = newPin;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) return false;
        if (enteredPin == pin) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;
            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                isLocked = true;
            }
            return false;
        }
    }

    public void deposit(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0) balance += amount;
        }
    }

    public void withdraw(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
            }
        }
    }
}

public class SecureBankAccountDemo {
    public static void main(String[] args) {
        SecureBankAccount acc = new SecureBankAccount("A101", 500);
        acc.setPin(1234);

        acc.deposit(200, 1234);
        System.out.println("Balance: " + acc.getBalance());

        acc.withdraw(100, 1234);
        System.out.println("Balance after withdrawal: " + acc.getBalance());

        // Wrong pin 3 times -> lock
        acc.withdraw(50, 1111);
        acc.withdraw(50, 1111);
        acc.withdraw(50, 1111);
        System.out.println("Locked? " + acc.isAccountLocked());
    }
}