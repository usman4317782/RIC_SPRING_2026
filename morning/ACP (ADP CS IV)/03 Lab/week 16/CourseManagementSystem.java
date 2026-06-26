import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Week 16: Comprehensive Capstone Project - Course Management System.
 * 
 * Combines:
 * 1. Swing GUI Components (JTabbedPane, JTable, JMenuBar, JComboBox, etc.)
 * 2. Events & Listeners (using modern Java Lambda expressions)
 * 3. Database operations (SQLite JDBC connection, PreparedStatements, DDL execution)
 * 4. Multithreading (Background status daemon thread updating clock timer)
 * 5. File I/O (Exporting formatted reports to disk)
 * 6. Structured Checked/Unchecked Exception handling
 */
public class CourseManagementSystem extends JFrame {
    private DefaultTableModel courseModel;
    private DefaultTableModel enrollmentModel;
    private JTable courseTable;
    private JTable enrollmentTable;
    
    // Course Inputs
    private JTextField courseCodeField;
    private JTextField courseTitleField;
    private JTextField creditHoursField;
    
    // Enrollment Inputs
    private JComboBox<String> courseSelectorCombo;
    private JTextField studentNameField;
    private JTextField studentRollField;

    // Status clock
    private JLabel clockLabel;
    private volatile boolean runClock = true;

    public CourseManagementSystem() {
        setTitle("Course & Enrollment Management System");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Initialize Database Tables
        setupDatabase();

        // 1. Setup Menu System (File IO Report Triggers)
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportReportItem = new JMenuItem("Export Reports...");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exportReportItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Bind Menu Actions via Lambdas
        exportReportItem.addActionListener(e -> exportReportToDisk());
        exitItem.addActionListener(e -> System.exit(0));

        // 2. Tabbed Panels Layout
        JTabbedPane tabbedPane = new JTabbedPane();

        // --- TAB 1: Courses Management Panel ---
        JPanel coursePanel = new JPanel(new BorderLayout(10, 10));
        coursePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Course Inputs Grid
        JPanel courseInputPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        courseInputPanel.add(new JLabel("Course Code (e.g. CS3623):"));
        courseInputPanel.add(new JLabel("Course Title:"));
        courseInputPanel.add(new JLabel("Credit Hours:"));
        
        courseCodeField = new JTextField();
        courseTitleField = new JTextField();
        creditHoursField = new JTextField();
        courseInputPanel.add(courseCodeField);
        courseInputPanel.add(courseTitleField);
        courseInputPanel.add(creditHoursField);
        coursePanel.add(courseInputPanel, BorderLayout.NORTH);

        // Course Data Grid JTable
        String[] courseHeaders = {"Code", "Title", "Credits"};
        courseModel = new DefaultTableModel(courseHeaders, 0);
        courseTable = new JTable(courseModel);
        coursePanel.add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Course Control Buttons
        JPanel courseButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton addCourseBtn = new JButton("Add Course");
        JButton deleteCourseBtn = new JButton("Delete Course");
        courseButtonsPanel.add(addCourseBtn);
        courseButtonsPanel.add(deleteCourseBtn);
        coursePanel.add(courseButtonsPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Courses Info", coursePanel);

        // --- TAB 2: Enrollments Panel ---
        JPanel enrollPanel = new JPanel(new BorderLayout(10, 10));
        enrollPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Enrollment Inputs Grid
        JPanel enrollInputPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        enrollInputPanel.add(new JLabel("Select Course:"));
        enrollInputPanel.add(new JLabel("Student Name:"));
        enrollInputPanel.add(new JLabel("Roll Number:"));

        courseSelectorCombo = new JComboBox<>();
        studentNameField = new JTextField();
        studentRollField = new JTextField();
        enrollInputPanel.add(courseSelectorCombo);
        enrollInputPanel.add(studentNameField);
        enrollInputPanel.add(studentRollField);
        enrollPanel.add(enrollInputPanel, BorderLayout.NORTH);

        // Enrollment Data Grid JTable
        String[] enrollHeaders = {"Enrollment ID", "Course Code", "Student Name", "Roll Number"};
        enrollmentModel = new DefaultTableModel(enrollHeaders, 0);
        enrollmentTable = new JTable(enrollmentModel);
        enrollPanel.add(new JScrollPane(enrollmentTable), BorderLayout.CENTER);

        // Enrollment Buttons
        JPanel enrollButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton enrollBtn = new JButton("Register Student");
        JButton dropBtn = new JButton("Drop Student");
        enrollButtonsPanel.add(enrollBtn);
        enrollButtonsPanel.add(dropBtn);
        enrollPanel.add(enrollButtonsPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Student Enrollments", enrollPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // 3. Status Bar at Bottom (Multithreading Clock)
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(new EmptyBorder(3, 8, 3, 8));
        statusBar.setBackground(new Color(0xE2E8F0));
        
        JLabel dbStatusLabel = new JLabel("Database status: Connected (SQLite)");
        dbStatusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        
        statusBar.add(dbStatusLabel, BorderLayout.WEST);
        statusBar.add(clockLabel, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        // Bind Button Handlers via Lambdas
        addCourseBtn.addActionListener(e -> addCourseHandler());
        deleteCourseBtn.addActionListener(e -> deleteCourseHandler());
        enrollBtn.addActionListener(e -> enrollStudentHandler());
        dropBtn.addActionListener(e -> dropStudentHandler());

        // Initialize display datasets and dropdowns
        refreshCoursesGrid();
        refreshEnrollmentsGrid();

        // 4. Start Multithreaded Status Clock Daemon Thread
        startClockDaemon();

        setLocationRelativeTo(null);
    }

    private void setupDatabase() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create Courses Table
            stmt.execute("CREATE TABLE IF NOT EXISTS courses (" +
                         "code TEXT PRIMARY KEY, " +
                         "title TEXT NOT NULL, " +
                         "credits INTEGER NOT NULL)");

            // Create Enrollments Table
            stmt.execute("CREATE TABLE IF NOT EXISTS enrollments (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "course_code TEXT, " +
                         "student_name TEXT, " +
                         "roll_number TEXT, " +
                         "FOREIGN KEY(course_code) REFERENCES courses(code) ON DELETE CASCADE)");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database DDL initialization failed: " + e.getMessage(),
                    "Setup Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Action Handlers ---

    private void addCourseHandler() {
        String code = courseCodeField.getText().trim().toUpperCase();
        String title = courseTitleField.getText().trim();
        String creditsStr = creditHoursField.getText().trim();

        if (code.isEmpty() || title.isEmpty() || creditsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All course fields are required!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int credits = Integer.parseInt(creditsStr);
            if (credits <= 0 || credits > 6) {
                JOptionPane.showMessageDialog(this, "Credits must be a number between 1 and 6.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO courses VALUES (?, ?, ?)")) {
                pstmt.setString(1, code);
                pstmt.setString(2, title);
                pstmt.setInt(3, credits);
                pstmt.executeUpdate();
                
                refreshCoursesGrid();
                
                // Clear fields
                courseCodeField.setText("");
                courseTitleField.setText("");
                creditHoursField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credit Hours must be a valid integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Could not insert course (Code may already exist): " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourseHandler() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a course from the grid to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String code = (String) courseModel.getValueAt(selectedRow, 0);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM courses WHERE code = ?")) {
            pstmt.setString(1, code);
            pstmt.executeUpdate();
            
            refreshCoursesGrid();
            refreshEnrollmentsGrid(); // cascade check
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enrollStudentHandler() {
        String courseCode = (String) courseSelectorCombo.getSelectedItem();
        String name = studentNameField.getText().trim();
        String roll = studentRollField.getText().trim();

        if (courseCode == null || name.isEmpty() || roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a course and fill in student details!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO enrollments (course_code, student_name, roll_number) VALUES (?, ?, ?)")) {
            pstmt.setString(1, courseCode);
            pstmt.setString(2, name);
            pstmt.setString(3, roll);
            pstmt.executeUpdate();
            
            refreshEnrollmentsGrid();
            
            studentNameField.setText("");
            studentRollField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Enrollment failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dropStudentHandler() {
        int selectedRow = enrollmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a student from the grid to drop.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (Integer) enrollmentModel.getValueAt(selectedRow, 0);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM enrollments WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            refreshEnrollmentsGrid();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Drop operation failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- UI Grid Synchronization ---

    private void refreshCoursesGrid() {
        courseModel.setRowCount(0);
        courseSelectorCombo.removeAllItems();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM courses ORDER BY code")) {
            
            while (rs.next()) {
                String code = rs.getString("code");
                courseModel.addRow(new Object[]{
                    code,
                    rs.getString("title"),
                    rs.getInt("credits")
                });
                courseSelectorCombo.addItem(code); // populate combo
            }
        } catch (SQLException e) {
            System.out.println("Courses refresh failed: " + e.getMessage());
        }
    }

    private void refreshEnrollmentsGrid() {
        enrollmentModel.setRowCount(0);

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM enrollments ORDER BY id")) {
            
            while (rs.next()) {
                enrollmentModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("course_code"),
                    rs.getString("student_name"),
                    rs.getString("roll_number")
                });
            }
        } catch (SQLException e) {
            System.out.println("Enrollments refresh failed: " + e.getMessage());
        }
    }

    // --- File I/O Report Export ---

    private void exportReportToDisk() {
        String fileName = "course_report.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=====================================================\n");
            writer.write("  COURSE AND STUDENT ENROLLMENT REPORT - CAPSTONE\n");
            writer.write("  Generated on: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
            writer.write("=====================================================\n\n");

            try (Connection conn = DBConnection.getConnection();
                 Statement stmtCourse = conn.createStatement()) {
                
                // Fetch all courses
                try (ResultSet rsCourse = stmtCourse.executeQuery("SELECT * FROM courses")) {
                    while (rsCourse.next()) {
                        String code = rsCourse.getString("code");
                        String title = rsCourse.getString("title");
                        int credits = rsCourse.getInt("credits");

                        writer.write(String.format("Course: %s - %s (%d Credits)\n", code, title, credits));
                        writer.write("-----------------------------------------------------\n");
                        
                        // Query students enrolled in THIS specific course
                        try (PreparedStatement pstmtEnroll = conn.prepareStatement(
                                "SELECT * FROM enrollments WHERE course_code = ?")) {
                            pstmtEnroll.setString(1, code);
                            try (ResultSet rsEnroll = pstmtEnroll.executeQuery()) {
                                int enrolledCount = 0;
                                while (rsEnroll.next()) {
                                    enrolledCount++;
                                    writer.write(String.format("  %d. %-15s (Roll: %s)\n", 
                                            enrolledCount, 
                                            rsEnroll.getString("student_name"), 
                                            rsEnroll.getString("roll_number")));
                                }
                                if (enrolledCount == 0) {
                                    writer.write("  (No students enrolled in this course)\n");
                                }
                            }
                        }
                        writer.write("\n");
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Report exported successfully to '" + fileName + "'!", 
                    "Export Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to write report file: " + ex.getMessage(), 
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Daemon Thread (Multithreading Clock) ---

    private void startClockDaemon() {
        Thread clockThread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (runClock) {
                // Update UI on Swings event dispatch thread safely
                javax.swing.SwingUtilities.invokeLater(() -> {
                    clockLabel.setText("Current Time: " + sdf.format(new Date()));
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        clockThread.setDaemon(true); // Flag as background daemon thread
        clockThread.start();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new CourseManagementSystem().setVisible(true));
    }
}
