import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 1: Implement prepared statements for secure database operations.
 * 
 * Demonstrates parameter binding to prevent SQL Injection and optimize execution performance.
 */
public class PreparedStatementDemo {
    public static void main(String[] args) {
        System.out.println("=== Secure Prepared Statements Demo ===");

        // Setup Database Table
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, department TEXT, gpa REAL)");
            stmt.execute("DELETE FROM students");
        } catch (SQLException e) {
            System.out.println("Setup failed: " + e.getMessage());
            return;
        }

        // 1. Parameterized Insert using PreparedStatement
        String insertSQL = "INSERT INTO students (name, department, gpa) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            
            System.out.println("Inserting records securely...");
            
            // Record 1
            pstmt.setString(1, "Alice Cooper");
            pstmt.setString(2, "Computer Science");
            pstmt.setDouble(3, 3.85);
            pstmt.executeUpdate();

            // Record 2
            pstmt.setString(1, "Bob Jones");
            pstmt.setString(2, "Software Engineering");
            pstmt.setDouble(3, 3.42);
            pstmt.executeUpdate();
            
            // Record 3 - Malicious payload simulation
            // In standard SQL, a name like "O'Brian" or "'; DROP TABLE students;--" would break 
            // the statement or execute injection queries. PreparedStatements escape values automatically!
            pstmt.setString(1, "O'Brian; DROP TABLE students; --"); 
            pstmt.setString(2, "History");
            pstmt.setDouble(3, 3.90);
            pstmt.executeUpdate();
            
            System.out.println("Successfully inserted 3 records (including SQL Injection payload safely escaped).");

        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }

        // 2. Parameterized Query using PreparedStatement
        String selectSQL = "SELECT * FROM students WHERE department = ? AND gpa >= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            
            pstmt.setString(1, "Computer Science");
            pstmt.setDouble(2, 3.50);
            
            System.out.println("\nQuerying: Department = 'Computer Science', GPA >= 3.50");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("  Found: ID = " + rs.getInt("id") + 
                                       " | Name = " + rs.getString("name") + 
                                       " | GPA = " + rs.getDouble("gpa"));
                }
            }

            // Let's verify the table 'students' was not dropped by the SQL Injection attempt
            System.out.println("\nVerifying 'students' table is intact (SQL Injection was successfully neutralized):");
            try (Statement checkStmt = conn.createStatement();
                 ResultSet checkRs = checkStmt.executeQuery("SELECT COUNT(*) FROM students")) {
                if (checkRs.next()) {
                    System.out.println("  Total students in database: " + checkRs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }
    }
}
