import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 3: Implement CRUD operations for a student database.
 */
public class StudentCRUD {

    // CREATE Operation
    public static void addStudent(String name, String department, double gpa) {
        String sql = String.format("INSERT INTO students (name, department, gpa) VALUES ('%s', '%s', %.2f)", 
                name, department, gpa);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("CRUD [CREATE]: Successfully added student '" + name + "'.");
        } catch (SQLException e) {
            System.out.println("CREATE Error: " + e.getMessage());
        }
    }

    // READ Operation
    public static void displayAllStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nCRUD [READ]: Current Database Student list:");
            System.out.printf("%-4s | %-15s | %-20s | %-4s\n", "ID", "Name", "Department", "GPA");
            System.out.println("---------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-4d | %-15s | %-20s | %.2f\n", 
                        rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("gpa"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("READ Error: " + e.getMessage());
        }
    }

    // UPDATE Operation
    public static void updateStudentGPA(int id, double newGpa) {
        String sql = String.format("UPDATE students SET gpa = %.2f WHERE id = %d", newGpa, id);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("CRUD [UPDATE]: Successfully updated GPA to " + newGpa + " for student ID " + id);
            } else {
                System.out.println("CRUD [UPDATE]: Student with ID " + id + " not found!");
            }
        } catch (SQLException e) {
            System.out.println("UPDATE Error: " + e.getMessage());
        }
    }

    // DELETE Operation
    public static void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = " + id;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("CRUD [DELETE]: Successfully deleted student ID " + id);
            } else {
                System.out.println("CRUD [DELETE]: Student with ID " + id + " not found!");
            }
        } catch (SQLException e) {
            System.out.println("DELETE Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Starting CRUD Operations Lifecycle Demo ===");

        // Drop and Recreate table so IDs always start at 1
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS students");
            stmt.execute("CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, department TEXT, gpa REAL)");
            System.out.println("Table reset successfully. IDs will start from 1.");
        } catch (SQLException e) {
            System.out.println("Table initialization failed: " + e.getMessage());
            return;
        }

        // 1. CREATE — will get IDs 1, 2, 3
        addStudent("Arthur Pendragon", "History", 3.75);
        addStudent("Guinevere Du Lac", "Literature", 3.91);
        addStudent("Lancelot Du Lac", "Physical Ed", 2.80);

        // 2. READ
        displayAllStudents();

        // 3. UPDATE (Boost Lancelot's GPA)
        updateStudentGPA(3, 3.45);
        displayAllStudents();

        // 4. DELETE (Remove Guinevere)
        deleteStudent(2);
        displayAllStudents();
    }
}
