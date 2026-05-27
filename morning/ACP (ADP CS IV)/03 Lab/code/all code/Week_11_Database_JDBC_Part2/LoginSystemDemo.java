import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Lab Task 4: Implement a simple login system with database authentication.
 * Features:
 *  - User registration (stores hashed password concept using SHA-256 simulation)
 *  - Login authentication against DB
 *  - Show welcome screen on successful login
 *  - Lockout after 3 failed attempts
 *
 * To compile: javac LoginSystemDemo.java
 * To run:     java -cp ".;mysql-connector-j.jar" LoginSystemDemo
 */
public class LoginSystemDemo extends JFrame {

    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER_DB = "root";
    private static final String PASS_DB = ""; // Update with your MySQL password

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private JLabel lblStatus;
    private int failedAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;

    public LoginSystemDemo() {
        setTitle("Login System - ACP Lab Week 11");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        setupDB();
        buildUI();
    }

    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Title
        JLabel lblTitle = new JLabel("Secure Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(33, 97, 140));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 12));
        formPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);
        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);
        formPanel.add(new JLabel()); // spacer

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 10, 0));
        btnLogin    = new JButton("Login");
        btnRegister = new JButton("Register");
        btnLogin.setBackground(new Color(33, 97, 140));
        btnLogin.setForeground(Color.WHITE);
        btnRegister.setBackground(new Color(39, 174, 96));
        btnRegister.setForeground(Color.WHITE);
        btnRow.add(btnLogin);
        btnRow.add(btnRegister);
        formPanel.add(btnRow);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Status
        lblStatus = new JLabel(" ", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        mainPanel.add(lblStatus, BorderLayout.SOUTH);

        add(mainPanel);

        // Enter key on password triggers login
        txtPassword.addActionListener(e -> performLogin());
        btnLogin.addActionListener(e -> performLogin());
        btnRegister.addActionListener(e -> performRegister());
    }

    private void performLogin() {
        if (failedAttempts >= MAX_ATTEMPTS) {
            showStatus("Account locked after " + MAX_ATTEMPTS + " failed attempts. Restart to try again.", Color.RED);
            btnLogin.setEnabled(false);
            return;
        }

        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showStatus("Please enter both username and password.", Color.ORANGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hashPassword(password));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    failedAttempts = 0;
                    showWelcomeScreen(rs.getString("username"), rs.getString("email"));
                } else {
                    failedAttempts++;
                    int remaining = MAX_ATTEMPTS - failedAttempts;
                    if (remaining > 0)
                        showStatus("Invalid credentials. " + remaining + " attempt(s) left.", Color.RED);
                    else {
                        showStatus("Account locked! Too many failed attempts.", Color.RED);
                        btnLogin.setEnabled(false);
                    }
                }
            }
        } catch (SQLException e) {
            showStatus("DB Error: " + e.getMessage(), Color.RED);
        }
    }

    private void performRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showStatus("Username and password required to register.", Color.ORANGE);
            return;
        }
        if (password.length() < 6) {
            showStatus("Password must be at least 6 characters.", Color.ORANGE);
            return;
        }

        String email = JOptionPane.showInputDialog(this, "Enter your email (optional):", "Register", JOptionPane.PLAIN_MESSAGE);
        if (email == null) return; // cancelled

        String sql = "INSERT INTO users (username, password_hash, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));
            pstmt.setString(3, email.trim());
            pstmt.executeUpdate();
            showStatus("User '" + username + "' registered successfully! You can now login.", new Color(0, 128, 0));
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate") || e.getMessage().contains("UNIQUE"))
                showStatus("Username already exists. Choose another.", Color.RED);
            else
                showStatus("Registration error: " + e.getMessage(), Color.RED);
        }
    }

    private void showWelcomeScreen(String username, String email) {
        JDialog dialog = new JDialog(this, "Welcome!", true);
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        JLabel lbl1 = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        lbl1.setFont(new Font("Arial", Font.BOLD, 18));
        lbl1.setForeground(new Color(39, 174, 96));
        JLabel lbl2 = new JLabel("Email: " + (email.isEmpty() ? "N/A" : email), SwingConstants.CENTER);
        JLabel lbl3 = new JLabel("Login successful!", SwingConstants.CENTER);

        panel.add(lbl1);
        panel.add(lbl2);
        panel.add(lbl3);

        JButton closeBtn = new JButton("Logout");
        closeBtn.addActionListener(e -> dialog.dispose());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(closeBtn, BorderLayout.SOUTH);
        dialog.setVisible(true);
        txtPassword.setText("");
    }

    private void setupDB() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) UNIQUE NOT NULL, " +
                "password_hash VARCHAR(64) NOT NULL, " +
                "email VARCHAR(100) DEFAULT '')");
            // Seed a default admin account (password: admin123)
            try {
                stmt.executeUpdate(
                    "INSERT INTO users (username, password_hash, email) VALUES " +
                    "('admin', '" + hashPassword("admin123") + "', 'admin@ric.edu.pk')");
            } catch (SQLException ignored) { /* already exists */ }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DB Setup Error: " + e.getMessage());
        }
    }

    /** Simple SHA-256 hex hash for demonstration purposes */
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            return password; // fallback (not for production)
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_DB, PASS_DB);
    }

    private void showStatus(String msg, Color color) {
        lblStatus.setText(msg);
        lblStatus.setForeground(color);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSystemDemo().setVisible(true));
    }
}
