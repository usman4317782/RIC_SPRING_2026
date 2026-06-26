import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Task 4: Create a program that uses lambda expressions for event handling.
 * 
 * Shows how Swing GUI event listeners are dramatically simplified using Lambda shorthand.
 */
public class LambdaEventHandling extends JFrame {
    private int counter = 0;
    private JLabel counterLabel;
    private JButton incrementButton;
    private JButton decrementButton;
    private JButton resetButton;

    public LambdaEventHandling() {
        setTitle("Lambda Event Counter GUI");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Counter Display Panel
        counterLabel = new JLabel("Counter Value: 0", JLabel.CENTER);
        counterLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(counterLabel, BorderLayout.CENTER);

        // 2. Control Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        incrementButton = new JButton("+ Increment");
        decrementButton = new JButton("- Decrement");
        resetButton = new JButton("Reset");

        buttonPanel.add(decrementButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(incrementButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling using Lambdas ---
        // Instead of anonymous inner classes, we use single-line/multi-line lambdas
        incrementButton.addActionListener(e -> {
            counter++;
            updateDisplay();
        });

        decrementButton.addActionListener(e -> {
            counter--;
            updateDisplay();
        });

        resetButton.addActionListener(e -> {
            counter = 0;
            updateDisplay();
        });

        setLocationRelativeTo(null);
    }

    private void updateDisplay() {
        counterLabel.setText("Counter Value: " + counter);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LambdaEventHandling().setVisible(true));
    }
}
