import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * Lab Task 4: Create a GUI that uses JTable to display tabular data.
 * 
 * To compile: javac JTableDemo.java
 * To run: java JTableDemo
 */
public class JTableDemo extends JFrame {
    public JTableDemo() {
        super("JTable Data Presentation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLayout(new BorderLayout());

        // Header Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Registered Students Directory", JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Column Names
        String[] columnNames = {"Roll Number", "Name", "Department", "GPA", "Status"};

        // Tabular Data
        Object[][] data = {
            {"CS-2026-001", "Alice Smith", "Computer Science", 3.82, "Active"},
            {"SE-2026-042", "Bob Jones", "Software Engineering", 3.45, "Active"},
            {"DS-2026-015", "Charlie Brown", "Data Science", 2.90, "On Probation"},
            {"IT-2026-009", "David Miller", "Information Tech.", 3.12, "Active"},
            {"CS-2026-024", "Emma Watson", "Computer Science", 3.95, "Active"},
            {"SE-2026-011", "Franklin Roosevelt", "Software Engineering", 3.61, "Suspended"}
        };

        // Table Model to prevent direct cell modification (read-only demo)
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are read-only
            }
        };

        // Create JTable with the model
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);

        // Customize column sizes
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        // Add Table to ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JTableDemo().setVisible(true);
        });
    }
}
