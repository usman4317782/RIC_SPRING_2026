import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Lab Task 3: Build a GUI front-end for database operations.
 * Features:
 *  - View all students in a JTable
 *  - Add new student record
 *  - Update selected student record
 *  - Delete selected student record
 *
 * To compile: javac StudentDatabaseGUI.java
 * To run:     java -cp ".;mysql-connector-j.jar" StudentDatabaseGUI
 */
public class StudentDatabaseGUI extends JFrame {

    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/acp_lab_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // Update with your MySQL password

    // UI Components
    private JTextField txtName, txtRollNo, txtDept, txtGpa;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private JLabel lblStatus;

    public StudentDatabaseGUI() {
        setTitle("Student Database Manager - ACP Lab Week 11");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);

        initComponents();
        setupTable();
        setupDB();
        loadStudents();
    }

    private void initComponents() {
        // ---- Form Panel (Left) ----
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        formPanel.setPreferredSize(new Dimension(260, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Name:", "Roll No:", "Department:", "GPA:"};
        JTextField[] fields = new JTextField[4];
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            fields[i] = new JTextField(14);
            formPanel.add(fields[i], gbc);
        }
        txtName   = fields[0];
        txtRollNo = fields[1];
        txtDept   = fields[2];
        txtGpa    = fields[3];

        // Buttons
        btnAdd     = new JButton("Add");
        btnUpdate  = new JButton("Update");
        btnDelete  = new JButton("Delete");
        btnClear   = new JButton("Clear");
        btnRefresh = new JButton("Refresh");

        btnAdd.setBackground(new Color(46, 139, 87));
        btnAdd.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(70, 130, 180));
        btnUpdate.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(178, 34, 34));
        btnDelete.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new GridLayout(3, 2, 6, 6));
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        btnPanel.add(btnRefresh);

        gbc.gridx = 0; gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        // ---- Table Panel (Right) ----
        String[] columns = {"ID", "Name", "Roll No", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        studentTable.setRowHeight(22);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));

        // ---- Status Bar ----
        lblStatus = new JLabel("Ready.");
        lblStatus.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        // ---- Layout ----
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        // ---- Listeners ----
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadStudents());

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                populateForm();
            }
        });
    }

    private void setupTable() { /* Columns already set in initComponents */ }

    private void setupDB() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "roll_no VARCHAR(50) UNIQUE NOT NULL, " +
                "department VARCHAR(100), " +
                "gpa DOUBLE)");
        } catch (SQLException e) {
            showError("DB Setup Error: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY id")) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("roll_no"),
                    rs.getString("department"),
                    String.format("%.2f", rs.getDouble("gpa"))
                });
            }
            lblStatus.setText("Loaded " + tableModel.getRowCount() + " record(s).");
        } catch (SQLException e) {
            showError("Load Error: " + e.getMessage());
        }
    }

    private void addStudent() {
        if (!validateForm()) return;
        String sql = "INSERT INTO students (name, roll_no, department, gpa) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtName.getText().trim());
            pstmt.setString(2, txtRollNo.getText().trim());
            pstmt.setString(3, txtDept.getText().trim());
            pstmt.setDouble(4, Double.parseDouble(txtGpa.getText().trim()));
            pstmt.executeUpdate();
            lblStatus.setText("Student added successfully.");
            clearForm();
            loadStudents();
        } catch (SQLException e) {
            showError("Add Error: " + e.getMessage());
        }
    }

    private void updateStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) { showError("Please select a record to update."); return; }
        if (!validateForm()) return;
        int id = (int) tableModel.getValueAt(row, 0);
        String sql = "UPDATE students SET name=?, roll_no=?, department=?, gpa=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtName.getText().trim());
            pstmt.setString(2, txtRollNo.getText().trim());
            pstmt.setString(3, txtDept.getText().trim());
            pstmt.setDouble(4, Double.parseDouble(txtGpa.getText().trim()));
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            lblStatus.setText("Record updated (ID=" + id + ").");
            clearForm();
            loadStudents();
        } catch (SQLException e) {
            showError("Update Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) { showError("Please select a record to delete."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete student with ID=" + id + "?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            lblStatus.setText("Record deleted (ID=" + id + ").");
            clearForm();
            loadStudents();
        } catch (SQLException e) {
            showError("Delete Error: " + e.getMessage());
        }
    }

    private void populateForm() {
        int row = studentTable.getSelectedRow();
        if (row == -1) return;
        txtName.setText((String) tableModel.getValueAt(row, 1));
        txtRollNo.setText((String) tableModel.getValueAt(row, 2));
        txtDept.setText((String) tableModel.getValueAt(row, 3));
        txtGpa.setText((String) tableModel.getValueAt(row, 4));
    }

    private void clearForm() {
        txtName.setText("");
        txtRollNo.setText("");
        txtDept.setText("");
        txtGpa.setText("");
        studentTable.clearSelection();
        lblStatus.setText("Form cleared.");
    }

    private boolean validateForm() {
        if (txtName.getText().trim().isEmpty() || txtRollNo.getText().trim().isEmpty()) {
            showError("Name and Roll No are required.");
            return false;
        }
        try {
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            if (gpa < 0 || gpa > 4.0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("GPA must be a number between 0.0 and 4.0.");
            return false;
        }
        return true;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        lblStatus.setText("Error: " + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentDatabaseGUI().setVisible(true));
    }
}
