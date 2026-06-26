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

/**
 * Task 4: Create a GUI that uses JTable to display tabular data.
 */
public class TableDisplayGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    
    // Inputs for adding new rows
    private JTextField idField;
    private JTextField nameField;
    private JTextField deptField;
    private JTextField gpaField;

    public TableDisplayGUI() {
        setTitle("Student Data Record Table");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // 1. Column headers
        String[] columns = {"Student ID", "Name", "Department", "GPA"};

        // 2. Initial Sample Dataset
        Object[][] data = {
            {"S101", "Alice Smith", "Computer Science", "3.85"},
            {"S102", "Bob Jones", "Software Engineering", "3.42"},
            {"S103", "Charlie Brown", "Information Technology", "3.68"}
        };

        // Create table model and bind to table
        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);
        
        // Add table to scroll pane
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. Add Input Panel at Top
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.setBorder(new javax.swing.border.EmptyBorder(8, 8, 8, 8));
        
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(new JLabel("Full Name:"));
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(new JLabel("GPA:"));

        idField = new JTextField();
        nameField = new JTextField();
        deptField = new JTextField();
        gpaField = new JTextField();

        inputPanel.add(idField);
        inputPanel.add(nameField);
        inputPanel.add(deptField);
        inputPanel.add(gpaField);

        add(inputPanel, BorderLayout.NORTH);

        // 4. Control buttons at Bottom
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = new JButton("Add Student Row");
        JButton deleteButton = new JButton("Delete Selected Row");
        
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add Row Listener
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String gpa = gpaField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || gpa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all text fields above first!", 
                        "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Verify GPA format
            try {
                double gpaValue = Double.parseDouble(gpa);
                if (gpaValue < 0.0 || gpaValue > 4.0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "GPA must be a number between 0.0 and 4.0!", 
                        "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Add new row to table model
            tableModel.addRow(new Object[]{id, name, dept, gpa});
            
            // Clear input fields
            idField.setText("");
            nameField.setText("");
            deptField.setText("");
            gpaField.setText("");
        });

        // Delete Row Listener
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please click a row in the table to select and delete it.", 
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new TableDisplayGUI().setVisible(true));
    }
}
