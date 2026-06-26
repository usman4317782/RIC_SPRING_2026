import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;

/**
 * Task 1: Create a simple window with basic components (JButton, JLabel, JTextField).
 */
public class SimpleWindow {
    public static void main(String[] args) {
        // Create the window frame container
        JFrame frame = new JFrame("Simple Window Demo");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout()); // Arrange components left-to-right

        // Create UI components
        JLabel label = new JLabel("Enter your name: ");
        JTextField textField = new JTextField(15);
        JButton button = new JButton("Submit");

        // Add components to the frame content pane
        frame.add(label);
        frame.add(textField);
        frame.add(button);

        // Center the frame on screen and make it visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
