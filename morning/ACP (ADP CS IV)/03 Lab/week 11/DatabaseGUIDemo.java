import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Task 3: Build a GUI front-end for database operations.
 */
public class DatabaseGUIDemo extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField deptField;
    private JTextField gpaField;

    public DatabaseGUIDemo() {
        setTitle("Student DB GUI Console");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Create Database Table on startup
        initializeDatabase();

        // 1. Table Setup
        String[] columns = {"ID", "Name", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load data from DB initially
        refreshTableData();

        // 2. Input Fields Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        inputPanel.setBorder(new javax.swing.border.EmptyBorder(8, 8, 8, 8));
        
        inputPanel.add(new JLabel("Full Name:"));
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(new JLabel("GPA:"));

        nameField = new JTextField();
        deptField = new JTextField();
        gpaField = new JTextField();

        inputPanel.add(nameField);
        inputPanel.add(deptField);
        inputPanel.add(gpaField);
        add(inputPanel, BorderLayout.NORTH);

        // 3. Buttons Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = new JButton("Insert to DB");
        JButton deleteButton = new JButton("Delete from DB");
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Insert Handler
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String gpaStr = gpaField.getText().trim();

            if (name.isEmpty() || dept.isEmpty() || gpaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all inputs!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double gpa = Double.parseDouble(gpaStr);
                
                // Write to DB
                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students (name, department, gpa) VALUES (?, ?, ?)")) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, dept);
                    pstmt.setDouble(3, gpa);
                    pstmt.executeUpdate();
                    
                    refreshTableData(); // Reload table
                    
                    // Clear inputs
                    nameField.setText("");
                    deptField.setText("");
                    gpaField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid GPA. Must be a decimal number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database write error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Handler
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student row in the table to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get ID from selected row (column index 0)
            Object idObj = tableModel.getValueAt(selectedRow, 0);
            int id = Integer.parseInt(idObj.toString());

            // Delete from DB
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE id = ?")) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                refreshTableData(); // Reload table
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database delete error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
    }

    private void initializeDatabase() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, department TEXT, gpa REAL)");
        } catch (SQLException e) {
            System.out.println("DB Init error: " + e.getMessage());
        }
    }

    private void refreshTableData() {
        // Clear old rows
        tableModel.setRowCount(0);

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("gpa")
                });
            }
        } catch (SQLException e) {
            System.out.println("DB Read error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new DatabaseGUIDemo().setVisible(true));
    }
}
