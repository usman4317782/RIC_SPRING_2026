import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Task 1: Implement action listeners for buttons in a calculator GUI.
 * 
 * To compile: javac CalculatorGUIDemo.java
 * To run: java CalculatorGUIDemo
 */
public class CalculatorGUIDemo extends JFrame {
    private JTextField displayField;
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public CalculatorGUIDemo() {
        super("Swing Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Create the display field
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        add(displayField, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Buttons layout
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Shared listener for buttons
        ButtonClickListener listener = new ButtonClickListener();

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(listener);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // Clear display
            if (command.equals("C")) {
                displayField.setText("0");
                firstOperand = 0;
                operator = "";
                isOperatorClicked = false;
            }
            // Number buttons
            else if ("0123456789".contains(command)) {
                if (displayField.getText().equals("0") || isOperatorClicked) {
                    displayField.setText(command);
                    isOperatorClicked = false;
                } else {
                    displayField.setText(displayField.getText() + command);
                }
            }
            // Operators
            else if ("+-*/".contains(command)) {
                firstOperand = Double.parseDouble(displayField.getText());
                operator = command;
                isOperatorClicked = true;
            }
            // Equals
            else if (command.equals("=")) {
                if (operator.isEmpty()) return;
                
                double secondOperand = Double.parseDouble(displayField.getText());
                double result = 0;

                switch (operator) {
                    case "+": result = firstOperand + secondOperand; break;
                    case "-": result = firstOperand - secondOperand; break;
                    case "*": result = firstOperand * secondOperand; break;
                    case "/": 
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand; 
                        } else {
                            displayField.setText("Error");
                            operator = "";
                            return;
                        }
                        break;
                }
                
                // Format decimal/integer output
                if (result % 1 == 0) {
                    displayField.setText(String.valueOf((int) result));
                } else {
                    displayField.setText(String.valueOf(result));
                }
                operator = "";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorGUIDemo().setVisible(true);
        });
    }
}
