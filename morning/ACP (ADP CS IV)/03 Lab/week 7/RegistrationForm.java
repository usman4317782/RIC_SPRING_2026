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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Task 4: Create a form with multiple input fields and a submit button.
 */
public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JButton submitButton;

    public RegistrationForm() {
        setTitle("User Registration Form");
        setSize(380, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create form panel with Grid layout for inputs
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        formPanel.setBorder(new EmptyBorder(15, 15, 5, 15));

        // Form Fields
        formPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 15, 15, 15));
        submitButton = new JButton("Register User");
        buttonPanel.add(submitButton);

        // Add panels to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ActionListener to submit the form data
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText().trim();

                // Basic validation
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "All fields are required! Please complete the form.",
                            "Validation Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    String successMessage = String.format(
                            "Registration Successful!\n\nName: %s\nEmail: %s\nPhone: %s\nPassword: [Hidden]",
                            name, email, phone);
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                            
                    // Clear fields after successful submit
                    nameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                    phoneField.setText("");
                }
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationForm().setVisible(true);
            }
        });
    }
}
