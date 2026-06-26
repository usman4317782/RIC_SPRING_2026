import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Task 1: Implement action listeners for buttons in a calculator GUI.
 */
public class CalculatorGUI extends JFrame {
    private JTextField displayField;
    private double num1 = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    public CalculatorGUI() {
        setTitle("Simple GUI Calculator");
        setSize(320, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Display screen at top
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 28));
        add(displayField, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        
        // Define button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Create and register listeners
        CalculatorButtonListener listener = new CalculatorButtonListener();

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(listener);
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private class CalculatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                // If a number button is pressed
                if (displayField.getText().equals("0") || isOperatorPressed) {
                    displayField.setText(command);
                    isOperatorPressed = false;
                } else {
                    displayField.setText(displayField.getText() + command);
                }
            } else if (command.equals("C")) {
                // Clear button
                displayField.setText("0");
                num1 = 0;
                operator = "";
                isOperatorPressed = false;
            } else if (command.equals("=")) {
                // Calculate result
                double num2 = Double.parseDouble(displayField.getText());
                double result = 0;
                boolean validCalculation = true;
                
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            displayField.setText("Error");
                            validCalculation = false;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                    default:
                        validCalculation = false;
                }
                
                if (validCalculation) {
                    // Check if result is whole number to format nicely
                    if (result == (long) result) {
                        displayField.setText(String.valueOf((long) result));
                    } else {
                        displayField.setText(String.valueOf(result));
                    }
                }
                operator = "";
            } else {
                // Operator (+, -, *, /) is pressed
                num1 = Double.parseDouble(displayField.getText());
                operator = command;
                isOperatorPressed = true;
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorGUI().setVisible(true);
            }
        });
    }
}
