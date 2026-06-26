import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Task 3: Build a temperature converter GUI (Celsius to Fahrenheit).
 */
public class TemperatureConverter extends JFrame {
    private JTextField celsiusField;
    private JTextField fahrenheitField;
    private JButton convertToFahButton;
    private JButton convertToCelButton;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 5, 5));

        // Row 1: Celsius Input
        JPanel celPanel = new JPanel(new FlowLayout());
        celPanel.add(new JLabel("Celsius (°C): "));
        celsiusField = new JTextField(10);
        celPanel.add(celsiusField);
        convertToFahButton = new JButton("Convert to °F >>");
        celPanel.add(convertToFahButton);

        // Row 2: Fahrenheit Input
        JPanel fahPanel = new JPanel(new FlowLayout());
        fahPanel.add(new JLabel("Fahrenheit (°F): "));
        fahrenheitField = new JTextField(10);
        fahPanel.add(fahrenheitField);
        convertToCelButton = new JButton("<< Convert to °C");
        fahPanel.add(convertToCelButton);

        // Row 3: Action feedback status
        JLabel statusLabel = new JLabel("Enter a value and click a convert button.", JLabel.CENTER);

        // Add action listeners
        convertToFahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celsius = Double.parseDouble(celsiusField.getText().trim());
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    fahrenheitField.setText(String.format("%.2f", fahrenheit));
                    statusLabel.setText(celsiusField.getText().trim() + " °C converted to " + String.format("%.2f", fahrenheit) + " °F");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TemperatureConverter.this, 
                            "Invalid Celsius input value! Please enter a valid number.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        convertToCelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(fahrenheitField.getText().trim());
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    celsiusField.setText(String.format("%.2f", celsius));
                    statusLabel.setText(fahrenheitField.getText().trim() + " °F converted to " + String.format("%.2f", celsius) + " °C");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TemperatureConverter.this, 
                            "Invalid Fahrenheit input value! Please enter a valid number.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add rows to frame
        add(celPanel);
        add(fahPanel);
        add(statusLabel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Run GUI thread safely
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}
