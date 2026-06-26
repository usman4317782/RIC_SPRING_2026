/**
 * Task 2: Implement a BankAccount class with deposit/withdraw methods.
 */
public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Set to 0.");
        }
    }
    
    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber + ". New Balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }
    
    // Withdraw Method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account " + accountNumber + ". Remaining Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds! Account " + accountNumber + " balance: $" + balance + ", attempted withdrawal: $" + amount);
        }
    }
    
    // Getter methods
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
}
