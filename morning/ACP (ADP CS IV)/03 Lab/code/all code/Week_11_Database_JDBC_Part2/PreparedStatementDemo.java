import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Lab Task 1: Implement prepared statements for secure database operations.
 * Demonstrates:
 * 1. The vulnerability of statement interpolation to SQL injection.
 * 2. Secure query handling using PreparedStatement.
 * 
 * To compile: javac PreparedStatementDemo.java
 * To run:     java -cp ".;mysql-connector-j.jar" PreparedStatementDemo
 */
public class PreparedStatementDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // Update with your MySQL password

    // Alternate SQLite Connection
    // private static final String DB_URL = "jdbc:sqlite:students.db";
    // private static final String USER = "";
    // private static final String PASS = "";

    public static void main(String[] args) {
        setupMockData();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connection established.");

            // 1. Demostrating SQL Injection using standard Statement
            System.out.println("\n===== Case A: SQL Injection vulnerability with Statement =====");
            // Simulated user inputs (one normal, one malicious)
            String normalInput = "CS-001";
            String hackerInput = "CS-001' OR '1'='1"; // Malicious SQL injection payload

            System.out.println("Hacker Inputs Roll Number: " + hackerInput);
            queryWithStatement(conn, hackerInput);

            // 2. Demonstrating SQL Injection mitigation using PreparedStatement
            System.out.println("\n===== Case B: SQL Injection protection with PreparedStatement =====");
            System.out.println("Hacker Inputs Roll Number: " + hackerInput);
            queryWithPreparedStatement(conn, hackerInput);

            // 3. Inserting data securely using PreparedStatement
            System.out.println("\n===== Case C: Inserting Data Securely with PreparedStatement =====");
            insertStudentSecurely(conn, "David Beckham", "CS-009", "Sports Management", 3.25);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Vulnerable query implementation
    private static void queryWithStatement(Connection conn, String rollNoInput) {
        String query = "SELECT * FROM students WHERE roll_no = '" + rollNoInput + "'";
        System.out.println("Generated SQL Statement: " + query);
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("Query Results:");
            while (rs.next()) {
                System.out.println(" - Student: " + rs.getString("name") + " | Roll: " + rs.getString("roll_no"));
            }
            System.out.println("(Notice how the hacker retrieved ALL students by injecting OR '1'='1)");
        } catch (SQLException e) {
            System.err.println("SQL Execution Error: " + e.getMessage());
        }
    }

    // Secure query implementation
    private static void queryWithPreparedStatement(Connection conn, String rollNoInput) {
        String query = "SELECT * FROM students WHERE roll_no = ?";
        System.out.println("Template SQL Statement: " + query);

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the parameter safely - driver escapes input characters automatically
            pstmt.setString(1, rollNoInput);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Query Results:");
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println(" - Student: " + rs.getString("name") + " | Roll: " + rs.getString("roll_no"));
                }
                if (count == 0) {
                    System.out.println("(No results returned. PreparedStatement treated the input literally as the roll number 'CS-001\\' OR \\'1\\'=\\'1')");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL PreparedStatement Error: " + e.getMessage());
        }
    }

    private static void insertStudentSecurely(Connection conn, String name, String rollNo, String dept, double gpa) {
        String insertSQL = "INSERT INTO students (name, roll_no, department, gpa) VALUES (?, ?, ?, ?) " +
                           "ON DUPLICATE KEY UPDATE name=?, department=?, gpa=?";
        
        if (DB_URL.contains("sqlite")) {
            insertSQL = "INSERT OR REPLACE INTO students (name, roll_no, department, gpa) VALUES (?, ?, ?, ?)";
        }

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, rollNo);
            pstmt.setString(3, dept);
            pstmt.setDouble(4, gpa);
            
            if (!DB_URL.contains("sqlite")) {
                pstmt.setString(5, name);
                pstmt.setString(6, dept);
                pstmt.setDouble(7, gpa);
            }

            int rows = pstmt.executeUpdate();
            System.out.println("Inserted/Updated student securely. Affected rows: " + rows);
        } catch (SQLException e) {
            System.err.println("Insert Statement Error: " + e.getMessage());
        }
    }

    private static void setupMockData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "roll_no VARCHAR(50) UNIQUE NOT NULL, " +
                    "department VARCHAR(100), " +
                    "gpa DOUBLE)";
            
            if (DB_URL.contains("sqlite")) {
                createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "roll_no TEXT UNIQUE NOT NULL, " +
                        "department TEXT, " +
                        "gpa REAL)";
            }
            stmt.executeUpdate(createTableSQL);

            // Clean start for test
            stmt.executeUpdate("DELETE FROM students");
            stmt.executeUpdate("INSERT INTO students (name, roll_no, department, gpa) VALUES ('Alice Smith', 'CS-001', 'Computer Science', 3.8)");
            stmt.executeUpdate("INSERT INTO students (name, roll_no, department, gpa) VALUES ('Bob Jones', 'SE-042', 'Software Engineering', 3.4)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
