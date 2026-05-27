/**
 * Lab Tasks 2 & 3: Implement a BankAccount class with deposit/withdraw methods.
 * Demonstrate creation and usage of multiple objects of this class.
 * 
 * To compile: javac BankAccountDemo.java
 * To run: java BankAccountDemo
 */
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f\n", amount, balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            // Bug fix: check for invalid amount FIRST before comparing with balance
            System.out.println("Invalid withdrawal amount. Must be greater than zero.");
        } else if (amount > balance) {
            System.out.printf("Error: Insufficient balance ($%.2f). Cannot withdraw $%.2f.%n", balance, amount);
        } else {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        }
    }

    // Check balance
    public void displayAccountInfo() {
        System.out.printf("Account Holder: %-15s | Account No: %-8s | Current Balance: $%.2f%n",
                accountHolderName, accountNumber, balance);
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        System.out.println("----- Bank Account Demo -----");
        
        // Create multiple bank accounts
        BankAccount account1 = new BankAccount("1001-A", "John Doe", 500.0);
        BankAccount account2 = new BankAccount("1002-B", "Jane Watson", 1500.0);
        
        System.out.println("Initial status of accounts:");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        
        System.out.println("\n--- Performing Transactions on John's Account ---");
        account1.deposit(250.50);
        account1.withdraw(100.0);
        account1.withdraw(800.0); // Should fail due to insufficient funds
        
        System.out.println("\n--- Performing Transactions on Jane's Account ---");
        account2.withdraw(500.0);
        account2.deposit(-50.0); // Should fail due to invalid amount
        
        System.out.println("\nFinal status of accounts:");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
    }
}
