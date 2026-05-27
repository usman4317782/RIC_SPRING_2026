/**
 * Lab Tasks 2 & 3: Create custom exception classes and throw them in a program.
 * Handle different types of exceptions (checked vs unchecked).
 * 
 * To compile: javac CustomExceptionDemo.java
 * To run: java CustomExceptionDemo
 */

// Custom Checked Exception (Extends Exception)
// Callers MUST either catch it or declare it in their 'throws' clause
class InsufficientFundsException extends Exception {
    private double deficit;
    
    public InsufficientFundsException(double deficit) {
        super("Transaction failed: Insufficient funds. You need $" + deficit + " more.");
        this.deficit = deficit;
    }
    
    public double getDeficit() {
        return deficit;
    }
}

// Custom Unchecked Exception (Extends RuntimeException)
// Does not need to be declared or caught explicitly at compile time
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Since InsufficientFundsException is a checked exception, we must declare 'throws'
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            double deficit = amount - balance;
            throw new InsufficientFundsException(deficit);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Remaining balance: $" + balance);
    }
}

public class CustomExceptionDemo {
    public static void validateAge(int age) {
        if (age < 18) {
            // Throwing unchecked exception
            throw new InvalidAgeException("Age is " + age + ". Must be 18 or above to create an account.");
        }
        System.out.println("Age is valid.");
    }

    public static void main(String[] args) {
        System.out.println("----- Custom Unchecked Exception Demo -----");
        try {
            validateAge(16); // Will throw exception
        } catch (InvalidAgeException e) {
            System.out.println("Caught Unchecked Exception: " + e.getMessage());
        }

        System.out.println("\n----- Custom Checked Exception Demo -----");
        BankAccount account = new BankAccount(100.0);
        try {
            System.out.println("Attempting to withdraw $150 from account...");
            account.withdraw(150.0); // Will throw checked exception
        } catch (InsufficientFundsException e) {
            System.out.println("Caught Checked Exception: " + e.getMessage());
            System.out.println("Deficit amount retrieved from exception: $" + e.getDeficit());
        }
    }
}
