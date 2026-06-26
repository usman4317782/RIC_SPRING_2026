import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 2: Create a program that handles transactions.
 * 
 * Simulates a bank transfer (withdrawing from one account and depositing to another) 
 * using transaction controls (commit and rollback).
 */
public class TransactionDemo {
    public static void main(String[] args) {
        System.out.println("=== Database Transaction Management Demo ===");

        // Setup Accounts table
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (acc_no TEXT PRIMARY KEY, owner TEXT, balance REAL)");
            stmt.execute("DELETE FROM accounts");
            
            // Insert initial balances
            stmt.executeUpdate("INSERT INTO accounts VALUES ('ACC1001', 'Alice', 1000.00)");
            stmt.executeUpdate("INSERT INTO accounts VALUES ('ACC1002', 'Bob', 500.00)");
            
            System.out.println("Initial balances loaded:");
            displayBalances(stmt);
        } catch (SQLException e) {
            System.out.println("Setup failed: " + e.getMessage());
            return;
        }

        // Scenario 1: A successful transaction (transfer $200 from Alice to Bob)
        System.out.println("\n--- SCENARIO 1: Successful Fund Transfer ($200) ---");
        try (Connection conn = DBConnection.getConnection()) {
            // 1. Disable Auto-Commit (starts transaction block)
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                // Deduct from Alice
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 200.00 WHERE acc_no = 'ACC1001'");
                
                // Add to Bob
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 200.00 WHERE acc_no = 'ACC1002'");
                
                // 2. Commit transaction
                conn.commit();
                System.out.println("Transaction committed successfully!");
            } catch (SQLException e) {
                // If any error occurred, rollback
                conn.rollback();
                System.out.println("Error occurred. Transaction rolled back: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Display balances after Scenario 1
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            displayBalances(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Scenario 2: A failed transaction resulting in rollback (transfer $500 to a non-existent account)
        System.out.println("\n--- SCENARIO 2: Failed Fund Transfer ($500) (Triggers Rollback) ---");
        try (Connection conn = DBConnection.getConnection()) {
            // Disable Auto-Commit
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                // Deduct from Alice
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 500.00 WHERE acc_no = 'ACC1001'");
                
                // Simulate an error: try to deduct from an account that results in negative balance
                // by inserting a DUPLICATE primary key which always fails in SQLite
                stmt.executeUpdate("INSERT INTO accounts VALUES ('ACC1001', 'Duplicate Error', 999.00)"); // UNIQUE constraint violation

                // Commit (won't reach here)
                conn.commit();
                System.out.println("Transaction committed!");
            } catch (SQLException e) {
                // Rollback changes
                conn.rollback();
                System.out.println("Catch block: Exception encountered! Transaction rolled back successfully.");
                System.out.println("Reason: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Display final balances (Alice's balance should still be 800, NOT 300, because of rollback)
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("\nBalances after failed transaction:");
            displayBalances(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayBalances(Statement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {
            while (rs.next()) {
                System.out.println("  " + rs.getString("owner") + " (" + rs.getString("acc_no") + "): $" + rs.getDouble("balance"));
            }
        }
    }
}
