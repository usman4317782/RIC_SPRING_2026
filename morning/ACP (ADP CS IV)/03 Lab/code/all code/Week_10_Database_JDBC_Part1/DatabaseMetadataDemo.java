import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Lab Task 4: Write a program that displays database metadata.
 * Displays both:
 * 1. DatabaseMetaData (DB product name, version, driver details)
 * 2. ResultSetMetaData (Table column count, names, data types from a query result)
 * 
 * To compile: javac DatabaseMetadataDemo.java
 * To run:     java -cp ".;mysql-connector-j.jar" DatabaseMetadataDemo
 */
public class DatabaseMetadataDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // Update with your MySQL password

    // Alternate SQLite Connection
    // private static final String DB_URL = "jdbc:sqlite:students.db";
    // private static final String USER = "";
    // private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connection established to read metadata.\n");

            // ==========================================
            // 1. DATABASE METADATA
            // ==========================================
            System.out.println("=========================================");
            System.out.println("         DATABASE METADATA INFO          ");
            System.out.println("=========================================");
            DatabaseMetaData dbmd = conn.getMetaData();
            
            System.out.println("Database Product Name:    " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("JDBC Driver Name:         " + dbmd.getDriverName());
            System.out.println("JDBC Driver Version:      " + dbmd.getDriverVersion());
            System.out.println("Database URL:             " + dbmd.getURL());
            System.out.println("Current User name:        " + dbmd.getUserName());
            System.out.println("ReadOnly state check:     " + conn.isReadOnly());
            System.out.println("=========================================\n");

            // Make sure the table exists before querying metadata on it
            try (Statement stmt = conn.createStatement()) {
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

                // Insert a dummy record if table is empty
                String checkQuery = "SELECT COUNT(*) FROM students";
                try (ResultSet rsCount = stmt.executeQuery(checkQuery)) {
                    if (rsCount.next() && rsCount.getInt(1) == 0) {
                        stmt.executeUpdate("INSERT INTO students (name, roll_no, department, gpa) VALUES ('Test Student', 'TEST-001', 'CS', 3.0)");
                    }
                }
            }

            // ==========================================
            // 2. RESULT SET METADATA
            // ==========================================
            System.out.println("=========================================");
            System.out.println("         RESULT SET METADATA INFO        ");
            System.out.println("=========================================");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                System.out.println("Total Columns in 'students' Table: " + columnCount);
                System.out.println("-----------------------------------------");
                System.out.printf("%-12s | %-12s | %-12s\n", "Column Name", "Data Type", "Auto-Increment");
                System.out.println("-----------------------------------------");
                
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnType = rsmd.getColumnTypeName(i);
                    boolean isAutoInc = rsmd.isAutoIncrement(i);
                    
                    System.out.printf("%-12s | %-12s | %-12b\n", columnName, columnType, isAutoInc);
                }
                System.out.println("=========================================");
            }

        } catch (SQLException e) {
            System.err.println("Database Metadata Error: " + e.getMessage());
        }
    }
}
