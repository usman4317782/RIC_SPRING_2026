import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 4: Write a program that displays database metadata.
 * 
 * Shows details about the DB itself (DatabaseMetaData) and details about 
 * query results (ResultSetMetaData).
 */
public class DatabaseMetadataDemo {
    public static void main(String[] args) {
        System.out.println("=== Database and Query Metadata Demo ===");

        try (Connection conn = DBConnection.getConnection()) {
            
            // Part 1: Retrieve DatabaseMetaData
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("--- Database Metadata Details ---");
            System.out.println("Database Product Name   : " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("JDBC Driver Name        : " + dbmd.getDriverName());
            System.out.println("JDBC Driver Version     : " + dbmd.getDriverVersion());
            System.out.println("Database URL            : " + dbmd.getURL());
            System.out.println("Read-Only Database?     : " + dbmd.isReadOnly());
            
            // List tables in database
            System.out.println("\nRetrieving tables list:");
            try (ResultSet tables = dbmd.getTables(null, null, "%", new String[]{"TABLE"})) {
                int count = 0;
                while (tables.next()) {
                    count++;
                    System.out.println("  Table #" + count + ": " + tables.getString("TABLE_NAME"));
                }
            }

            // Part 2: Retrieve ResultSetMetaData
            System.out.println("\n--- Query ResultSet Metadata Details ---");
            
            // Ensure table exists to query
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, department TEXT, gpa REAL)");
                
                // Query table to inspect Result Set metadata
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    
                    int columnCount = rsmd.getColumnCount();
                    System.out.println("Total columns in 'students' query result: " + columnCount);
                    System.out.println("Column specifications:");
                    
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        String columnType = rsmd.getColumnTypeName(i);
                        int columnDisplaySize = rsmd.getColumnDisplaySize(i);
                        boolean isNullable = (rsmd.isNullable(i) == ResultSetMetaData.columnNullable);
                        
                        System.out.printf("  Column #%d: Name = %-12s | Type = %-8s | Display Size = %-3d | Nullable = %s\n",
                                i, columnName, columnType, columnDisplaySize, isNullable);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Metadata query failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
