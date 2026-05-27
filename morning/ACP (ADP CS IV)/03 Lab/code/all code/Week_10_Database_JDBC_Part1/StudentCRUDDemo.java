import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Lab Tasks 1, 2, & 3: Set up a database connection with MySQL/JavaDB.
 * Create a program that executes simple SQL queries and implements CRUD operations.
 * 
 * To run this program:
 * 1. Ensure you have a database server running (e.g. MySQL).
 * 2. Create a database named 'acp_lab_db'.
 * 3. Make sure the MySQL JDBC Connector JAR is added to your project dependencies / classpath.
 *    Command to compile: javac StudentCRUDDemo.java
 *    Command to run:     java -cp ".;mysql-connector-j.jar" StudentCRUDDemo
 * 
 * Note: You can switch to SQLite by uncommenting the SQLite driver and URL below (requires no setup).
 */
public class StudentCRUDDemo {
    // Database credentials for MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // Update with your MySQL password

    // Alternate SQLite Connection (No database server required, just requires sqlite-jdbc jar)
    // private static final String DB_URL = "jdbc:sqlite:students.db";
    // private static final String USER = "";
    // private static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. Establish Connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection established successfully!");

            // 2. Create Table
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "roll_no VARCHAR(50) UNIQUE NOT NULL, " +
                    "department VARCHAR(100), " +
                    "gpa DOUBLE)";
            
            // Adjust statement for SQLite if using SQLite
            if (DB_URL.contains("sqlite")) {
                createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "roll_no TEXT UNIQUE NOT NULL, " +
                        "department TEXT, " +
                        "gpa REAL)";
            }

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'students' checked/created successfully.");

            // Clear old entries for a clean run
            stmt.executeUpdate("DELETE FROM students");
            System.out.println("Cleared previous students for a clean demonstration.");

            // 3. INSERT (CREATE)
            System.out.println("\n--- Performing INSERT (Create) ---");
            executeInsert(stmt, "Alice Smith", "CS-001", "Computer Science", 3.8);
            executeInsert(stmt, "Bob Jones", "SE-042", "Software Engineering", 3.4);
            executeInsert(stmt, "Charlie Brown", "DS-015", "Data Science", 2.9);

            // 4. SELECT (READ)
            System.out.println("\n--- Performing SELECT (Read) ---");
            displayAllStudents(stmt);

            // 5. UPDATE
            System.out.println("\n--- Performing UPDATE ---");
            String updateSQL = "UPDATE students SET gpa = 3.95, department = 'Artificial Intelligence' WHERE roll_no = 'CS-001'";
            int rowsUpdated = stmt.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowsUpdated);

            // Verify update
            displayAllStudents(stmt);

            // 6. DELETE
            System.out.println("\n--- Performing DELETE ---");
            String deleteSQL = "DELETE FROM students WHERE roll_no = 'DS-015'";
            int rowsDeleted = stmt.executeUpdate(deleteSQL);
            System.out.println("Rows deleted: " + rowsDeleted);

            // Verify final list
            System.out.println("\n--- Final Students Database Table Contents ---");
            displayAllStudents(stmt);

        } catch (SQLException se) {
            System.err.println("JDBC / SQL Error: " + se.getMessage());
            se.printStackTrace();
        } finally {
            // Clean-up resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\nDatabase resources closed.");
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void executeInsert(Statement stmt, String name, String rollNo, String dept, double gpa) throws SQLException {
        String insertSQL = String.format(
                "INSERT INTO students (name, roll_no, department, gpa) VALUES ('%s', '%s', '%s', %.2f)",
                name, rollNo, dept, gpa
        );
        int rows = stmt.executeUpdate(insertSQL);
        System.out.println("Inserted student '" + name + "'. Affected rows: " + rows);
    }

    private static void displayAllStudents(Statement stmt) throws SQLException {
        String query = "SELECT id, name, roll_no, department, gpa FROM students";
        try (ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("ID  | Name            | Roll No. | Department           | GPA");
            System.out.println("-------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String rollNo = rs.getString("roll_no");
                String dept = rs.getString("department");
                double gpa = rs.getDouble("gpa");
                System.out.printf("%-3d | %-15s | %-8s | %-20s | %.2f\n", id, name, rollNo, dept, gpa);
            }
        }
    }
}
