import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Task 1: Create a student registration form with various Swing components.
 * 
 * To compile: javac StudentRegistrationForm.java
 * To run: java StudentRegistrationForm
 */
public class StudentRegistrationForm extends JFrame {
    private JTextField nameField, rollField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> departmentCombo;
    private JCheckBox termsCheck;
    private JTextArea outputArea;
    private JButton submitButton, clearButton;

    public StudentRegistrationForm() {
        super("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLayout(new BorderLayout(10, 10));

        // Form Title
        JLabel titleLabel = new JLabel("University Registration Form", JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Main Input Form Panel (Using GridBagLayout for clean form spacing)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // 2. Roll Number
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Roll Number:"), gbc);
        gbc.gridx = 1; rollField = new JTextField(15);
        formPanel.add(rollField, gbc);

        // 3. Gender (Radio Buttons)
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Gender:"), gbc);
        
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        // 4. Department (ComboBox)
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Department:"), gbc);
        
        String[] departments = {"Computer Science", "Software Engineering", "Information Technology", "Data Science"};
        departmentCombo = new JComboBox<>(departments);
        gbc.gridx = 1;
        formPanel.add(departmentCombo, gbc);

        // 5. Terms & Conditions (CheckBox)
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        termsCheck = new JCheckBox("I accept the terms and conditions");
        formPanel.add(termsCheck, gbc);

        // Reset grid width
        gbc.gridwidth = 1;

        // Buttons
        gbc.gridy = 5;
        gbc.gridx = 0;
        submitButton = new JButton("Submit");
        formPanel.add(submitButton, gbc);
        
        gbc.gridx = 1;
        clearButton = new JButton("Clear");
        formPanel.add(clearButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Display results area at the bottom
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        southPanel.add(new JLabel("Submission Output:"), BorderLayout.NORTH);
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        southPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Action Listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String roll = rollField.getText().trim();
                String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
                String dept = (String) departmentCombo.getSelectedItem();
                boolean terms = termsCheck.isSelected();

                if (name.isEmpty() || roll.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentRegistrationForm.this,
                            "Please fill in all details and select a gender.",
                            "Form Incomplete",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!terms) {
                    JOptionPane.showMessageDialog(StudentRegistrationForm.this,
                            "You must accept the terms and conditions to proceed.",
                            "Terms Unaccepted",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Output details
                String registrationText = String.format(
                        "=== REGISTRATION SUCCESSFUL ===\nName: %s\nRoll Number: %s\nGender: %s\nDepartment: %s\n",
                        name, roll, gender, dept);
                outputArea.setText(registrationText);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                rollField.setText("");
                genderGroup.clearSelection();
                departmentCombo.setSelectedIndex(0);
                termsCheck.setSelected(false);
                outputArea.setText("");
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationForm().setVisible(true);
        });
    }
}
