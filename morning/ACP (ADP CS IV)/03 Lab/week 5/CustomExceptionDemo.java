/**
 * Task 2: Create custom exception classes and throw them in a program.
 */

// Custom Checked Exception (extends Exception)
class InsufficientFundsException extends Exception {
    private double balance;
    private double amountRequested;
    
    public InsufficientFundsException(double balance, double amountRequested) {
        super(String.format("Insufficient funds! Balance: $%.2f, requested: $%.2f", balance, amountRequested));
        this.balance = balance;
        this.amountRequested = amountRequested;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getAmountRequested() {
        return amountRequested;
    }
}

// Class using custom exceptions
class BankAccountWithException {
    private double balance;
    
    public BankAccountWithException(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            // Throw custom exception
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! New balance: $" + balance);
    }
}

// Main Driver Class
public class CustomExceptionDemo {
    public static void main(String[] args) {
        System.out.println("--- Custom Exception Demo ---");
        BankAccountWithException account = new BankAccountWithException(200.00);
        
        try {
            System.out.println("Attempting withdrawal of $150...");
            account.withdraw(150.00);
            
            System.out.println("Attempting withdrawal of $100...");
            account.withdraw(100.00); // This will trigger the exception
        } catch (InsufficientFundsException e) {
            System.out.println("Catch Block: Caught custom exception -> " + e.getMessage());
            System.out.println("Current Balance: $" + e.getBalance());
            System.out.println("Attempted Withdrawal Amount: $" + e.getAmountRequested());
        }
    }
}
