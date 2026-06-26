import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class to get SQLite database connection.
 * 
 * Works with the downloaded SQLite JDBC driver.
 */
public class DBConnection {
    // Relative path to the SQLite database file
    private static final String DB_URL = "jdbc:sqlite:acp_lab.db";
    
    public static Connection getConnection() throws SQLException {
        try {
            // Load SQLite driver class dynamically
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found in Classpath! Make sure sqlite-jdbc.jar is linked.");
        }
        return DriverManager.getConnection(DB_URL);
    }
}
