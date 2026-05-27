import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Lab Task 2: Create a program that handles transactions.
 * Simulates a bank transfer from Account A to Account B.
 * Demonstrates:
 * 1. Disabling auto-commit.
 * 2. Successful transaction (commit).
 * 3. Failed transaction rollback (recovering initial states on error).
 * 
 * To compile: javac DatabaseTransactionDemo.java
 * To run:     java -cp ".;mysql-connector-j.jar" DatabaseTransactionDemo
 */
public class DatabaseTransactionDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // Update with your MySQL password

    // Alternate SQLite Connection
    // private static final String DB_URL = "jdbc:sqlite:students.db";
    // private static final String USER = "";
    // private static final String PASS = "";

    public static void main(String[] args) {
        setupAccountsTable();

        System.out.println("===== Transaction Execution Demo =====");
        
        // 1. Run a successful transaction
        System.out.println("\n--- Transferring $200 from Alice to Bob (Should succeed) ---");
        performTransfer("Alice", "Bob", 200.0);

        // Check balances
        displayBalances();

        // 2. Run a failed transaction (Bob transfers to non-existent account, or amount is too high, etc.)
        System.out.println("\n--- Transferring $1000 from Bob to Charlie (HACK/FAIL: Charlie's account doesn't exist) ---");
        performTransfer("Bob", "Charlie", 1000.0);

        // Check balances (should remain unchanged from the previous check due to rollback)
        displayBalances();
    }

    private static void performTransfer(String fromUser, String toUser, double amount) {
        Connection conn = null;
        PreparedStatement withdrawStmt = null;
        PreparedStatement depositStmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Turn off auto-commit to control the transaction manually
            conn.setAutoCommit(false);

            // Step 1: Withdraw money from fromUser
            String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE name = ?";
            withdrawStmt = conn.prepareStatement(withdrawSQL);
            withdrawStmt.setDouble(1, amount);
            withdrawStmt.setString(2, fromUser);
            int rowsWithdrawn = withdrawStmt.executeUpdate();

            if (rowsWithdrawn == 0) {
                throw new SQLException("Sender account '" + fromUser + "' not found.");
            }

            // Step 2: Deposit money to toUser
            String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE name = ?";
            depositStmt = conn.prepareStatement(depositSQL);
            depositStmt.setDouble(1, amount);
            depositStmt.setString(2, toUser);
            int rowsDeposited = depositStmt.executeUpdate();

            if (rowsDeposited == 0) {
                // If receiver account is not found, throw exception to trigger rollback
                throw new SQLException("Receiver account '" + toUser + "' not found. Aborting transaction.");
            }

            // If both operations succeeded, commit the transaction
            conn.commit();
            System.out.println("Transaction committed successfully!");

        } catch (SQLException e) {
            System.err.println("Transaction Failed! Reason: " + e.getMessage());
            if (conn != null) {
                try {
                    System.out.println("Rolling back transaction to restore initial states...");
                    conn.rollback();
                    System.out.println("Rollback completed.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Restore default auto-commit state and close resources
            try {
                if (withdrawStmt != null) withdrawStmt.close();
                if (depositStmt != null) depositStmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setupAccountsTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "name VARCHAR(100) PRIMARY KEY, " +
                    "balance DOUBLE NOT NULL)";
            
            if (DB_URL.contains("sqlite")) {
                createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                        "name TEXT PRIMARY KEY, " +
                        "balance REAL NOT NULL)";
            }
            
            stmt.executeUpdate(createTableSQL);
            stmt.executeUpdate("DELETE FROM accounts");
            stmt.executeUpdate("INSERT INTO accounts (name, balance) VALUES ('Alice', 1000.0)");
            stmt.executeUpdate("INSERT INTO accounts (name, balance) VALUES ('Bob', 500.0)");
            System.out.println("Table 'accounts' initialized with sample data.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayBalances() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {
            
            System.out.println("Account Name | Current Balance");
            System.out.println("------------------------------");
            while (rs.next()) {
                System.out.printf("%-12s | $%.2f\n", rs.getString("name"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
