import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Task 3: Build a temperature converter GUI (Celsius to Fahrenheit).
 * 
 * To compile: javac TemperatureConverter.java
 * To run: java TemperatureConverter
 */
public class TemperatureConverter extends JFrame {
    private JTextField celsiusField;
    private JLabel resultLabel;
    private JButton convertButton;

    public TemperatureConverter() {
        super("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 150);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        // Create Components
        add(new JLabel("Enter Celsius:"));
        celsiusField = new JTextField(8);
        add(celsiusField);

        convertButton = new JButton("Convert");
        add(convertButton);

        resultLabel = new JLabel("Fahrenheit: -- °F");
        add(resultLabel);

        // Add action listener to button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celsius = Double.parseDouble(celsiusField.getText());
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    resultLabel.setText(String.format("Fahrenheit: %.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TemperatureConverter.this,
                            "Please enter a valid numeric value.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemperatureConverter().setVisible(true);
        });
    }
}
