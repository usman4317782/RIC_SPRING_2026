import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 4: Implement a simple login system with database authentication.
 */
public class LoginSystem extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginSystem() {
        setTitle("Secure User Login Console");
        setSize(360, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create Database Users table
        initializeDatabase();

        // 1. Grid inputs panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        inputPanel.setBorder(new EmptyBorder(15, 15, 5, 15));
        
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        add(inputPanel, BorderLayout.CENTER);

        // 2. Control Buttons Panel
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register New Account");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Login Action Listener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful! Welcome, " + username + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Access Denied: Invalid Username or Password!", "Authentication Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Register Action Listener
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete username and password fields to register!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Account created successfully! You can now log in.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed: Username may already exist.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
    }

    private void initializeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Create user accounts table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Login DB Init failed: " + e.getMessage());
        }
    }

    // Helper method to query db for credential matching
    private boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if a match is found
            }
        } catch (SQLException e) {
            System.out.println("Authentication Error: " + e.getMessage());
            return false;
        }
    }

    // Helper method to write new user credentials
    private boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration Write Error: " + e.getMessage());
            return false; // Unique constraint violation if username already exists
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LoginSystem().setVisible(true));
    }
}
