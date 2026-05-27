import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Task 4: Create a form with multiple input fields and a submit button.
 * 
 * To compile: javac FormGUIDemo.java
 * To run: java FormGUIDemo
 */
public class FormGUIDemo extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton submitButton;

    public FormGUIDemo() {
        super("User Information Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);

        // Layout with a grid panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        mainPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        mainPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        mainPanel.add(phoneField);

        mainPanel.add(new JLabel("")); // Empty cell for spacing
        submitButton = new JButton("Submit Form");
        mainPanel.add(submitButton);

        add(mainPanel);

        // Action Listener for Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(FormGUIDemo.this,
                            "All fields are mandatory. Please fill them out.",
                            "Form Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String info = String.format("Form Submitted Successfully!\n\nName: %s\nEmail: %s\nPhone: %s", 
                            name, email, phone);
                    JOptionPane.showMessageDialog(FormGUIDemo.this, info, "Submission Info", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Clear fields after submission
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                }
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormGUIDemo().setVisible(true);
        });
    }
}
