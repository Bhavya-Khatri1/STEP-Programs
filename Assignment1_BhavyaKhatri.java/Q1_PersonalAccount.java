// Q1_PersonalAccount

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    private static int totalAccounts = 0;
    private static String bankName = "MyBank";

    // Constructor
    public PersonalAccount(String name) {
        this.accountHolderName = name;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    // Methods
    public void addIncome(double amount, String description) {
        if (amount <= 0) return;
        totalIncome += amount;
        currentBalance += amount;
        System.out.println("Income added: " + amount + " (" + description + ")");
    }

    public void addExpense(double amount, String description) {
        if (amount <= 0) return;
        totalExpenses += amount;
        currentBalance -= amount;
        System.out.println("Expense added: " + amount + " (" + description + ")");
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account No: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
    }

    // Static methods
    public static void setBankName(String name) { bankName = name; }
    public static int getTotalAccounts() { return totalAccounts; }
    public static String generateAccountNumber() {
        return "AC" + (1000 + totalAccounts);
    }
}

public class Q1_PersonalAccount {
    public static void main(String[] args) {
        // Set bank name for all accounts
        PersonalAccount.setBankName("Campus Bank");

        // Create accounts
        PersonalAccount a1 = new PersonalAccount("Alice");
        PersonalAccount a2 = new PersonalAccount("Bob");
        PersonalAccount a3 = new PersonalAccount("Charlie");

        // Perform transactions
        a1.addIncome(5000, "Salary");
        a1.addExpense(1200, "Rent");
        a1.displayAccountSummary();

        a2.addIncome(2000, "Freelance");
        a2.addExpense(300, "Groceries");
        a2.displayAccountSummary();

        a3.addIncome(10000, "Project");
        a3.addExpense(2500, "Laptop");
        a3.displayAccountSummary();

        // total accounts created
        System.out.println("\nTotal accounts created: " + PersonalAccount.getTotalAccounts());
    }
}