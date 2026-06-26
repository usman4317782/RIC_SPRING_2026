import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 1: Create a student registration form with various Swing components.
 */
public class StudentRegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField rollNoField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> departmentCombo;
    private JTextArea addressArea;
    private JButton submitButton;

    public StudentRegistrationForm() {
        setTitle("Comprehensive Student Registration Form");
        setSize(460, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Master panel using GridBagLayout for flexible alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // 2. Roll Number
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Roll Number:"), gbc);
        gbc.gridx = 1;
        rollNoField = new JTextField(20);
        formPanel.add(rollNoField, gbc);

        // 3. Gender (Radio Buttons inside button group)
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        formPanel.add(genderPanel, gbc);

        // 4. Department (Combo Box)
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        String[] depts = {"Computer Science", "Software Engineering", "Information Technology", "Electrical Engineering"};
        departmentCombo = new JComboBox<>(depts);
        formPanel.add(departmentCombo, gbc);

        // 5. Hobbies (Checkboxes)
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Hobbies:"), gbc);
        gbc.gridx = 1;
        JPanel hobbiesPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        sportsCheck = new JCheckBox("Sports ");
        musicCheck = new JCheckBox("Music ");
        readingCheck = new JCheckBox("Reading");
        hobbiesPanel.add(sportsCheck);
        hobbiesPanel.add(musicCheck);
        hobbiesPanel.add(readingCheck);
        formPanel.add(hobbiesPanel, gbc);

        // 6. Address (Text Area)
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressArea = new JTextArea(4, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        formPanel.add(new JScrollPane(addressArea), gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Submit Registration");
        formPanel.add(submitButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Submit Action Listener
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String rollNo = rollNoField.getText().trim();
            
            String gender = "";
            if (maleRadio.isSelected()) gender = "Male";
            else if (femaleRadio.isSelected()) gender = "Female";
            else if (otherRadio.isSelected()) gender = "Other";

            String dept = (String) departmentCombo.getSelectedItem();

            List<String> hobbies = new ArrayList<>();
            if (sportsCheck.isSelected()) hobbies.add("Sports");
            if (musicCheck.isSelected()) hobbies.add("Music");
            if (readingCheck.isSelected()) hobbies.add("Reading");

            String address = addressArea.getText().trim();

            // Simple validation
            if (name.isEmpty() || rollNo.isEmpty() || gender.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields (Name, Roll No, Gender, Address)!",
                        "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Display results
            String summary = String.format(
                    "Student Registered!\n\nName: %s\nRoll No: %s\nGender: %s\nDepartment: %s\nHobbies: %s\nAddress: %s",
                    name, rollNo, gender, dept, hobbies.isEmpty() ? "None" : hobbies.toString(), address
            );

            JOptionPane.showMessageDialog(this, summary, "Registration Summary", JOptionPane.INFORMATION_MESSAGE);
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new StudentRegistrationForm().setVisible(true));
    }
}
