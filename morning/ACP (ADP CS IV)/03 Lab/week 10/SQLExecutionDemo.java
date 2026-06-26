import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 2: Create a program that executes simple SQL queries.
 * 
 * Creates a table, inserts records, and executes a select query.
 */
public class SQLExecutionDemo {
    public static void main(String[] args) {
        System.out.println("--- Simple SQL Execution Demo ---");
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // 1. Create Table query
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "name TEXT NOT NULL, " +
                                    "department TEXT, " +
                                    "gpa REAL)";
            stmt.execute(createTableSQL);
            System.out.println("1. Table 'students' created or verified successfully.");

            // 2. Clear old data for demo run
            stmt.executeUpdate("DELETE FROM students");

            // 3. Insert records queries
            stmt.executeUpdate("INSERT INTO students (name, department, gpa) VALUES ('John Doe', 'Computer Science', 3.82)");
            stmt.executeUpdate("INSERT INTO students (name, department, gpa) VALUES ('Jane Smith', 'Software Engineering', 3.95)");
            stmt.executeUpdate("INSERT INTO students (name, department, gpa) VALUES ('Bob Miller', 'Information Technology', 3.12)");
            System.out.println("2. Three records inserted successfully.");

            // 4. Select records query
            System.out.println("\n3. Executing SELECT query and displaying results:");
            String selectSQL = "SELECT * FROM students";
            try (ResultSet rs = stmt.executeQuery(selectSQL)) {
                System.out.printf("%-5s | %-12s | %-22s | %-4s\n", "ID", "Name", "Department", "GPA");
                System.out.println("----------------------------------------------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String dept = rs.getString("department");
                    double gpa = rs.getDouble("gpa");
                    System.out.printf("%-5d | %-12s | %-22s | %.2f\n", id, name, dept, gpa);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
