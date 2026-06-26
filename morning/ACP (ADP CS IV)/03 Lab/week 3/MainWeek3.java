/**
 * Task 3: Create multiple objects of these classes and demonstrate their usage.
 */
public class MainWeek3 {
    public static void main(String[] args) {
        System.out.println("=== Demonstrating Student Class Objects ===");
        // Create student objects using both constructors
        Student student1 = new Student("Alice Smith", "CS101", 19);
        Student student2 = new Student(); // Default constructor
        
        // Modifying default student object values
        student2.setName("Bob Jones");
        student2.setRollNo("CS102");
        student2.setAge(21);
        
        // Displaying details
        student1.displayDetails();
        student2.displayDetails();
        
        System.out.println("\n=== Demonstrating BankAccount Class Objects ===");
        // Create bank account objects
        BankAccount account1 = new BankAccount("ACT-9081", "Alice Smith", 500.00);
        BankAccount account2 = new BankAccount("ACT-9082", "Bob Jones", 150.00);
        
        // Perform transactions on account 1
        account1.deposit(200.00);
        account1.withdraw(150.00);
        account1.withdraw(600.00); // Should fail due to insufficient funds
        
        // Perform transactions on account 2
        account2.withdraw(50.00);
        account2.deposit(-20.00); // Should fail due to negative input
        
        System.out.println("\nFinal Balances:");
        System.out.println(account1.getAccountHolderName() + " Balance: $" + account1.getBalance());
        System.out.println(account2.getAccountHolderName() + " Balance: $" + account2.getBalance());
    }
}
