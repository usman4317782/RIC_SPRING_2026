import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Tasks 1 & 2: Create a simple window with basic components and experiment with layout managers.
 * Shows three layout panels side-by-side inside a main frame.
 * 
 * To compile: javac SimpleGUIDemo.java
 * To run: java SimpleGUIDemo
 */
public class SimpleGUIDemo {
    public static void main(String[] args) {
        // Run GUI code on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Layout Managers & Components Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 300);
            frame.setLayout(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns

            // 1. Panel with FlowLayout
            JPanel flowPanel = new JPanel(new FlowLayout());
            flowPanel.add(new JLabel("FlowLayout Panel"));
            flowPanel.add(new JTextField("Type here...", 15));
            flowPanel.add(new JButton("Flow Button 1"));
            flowPanel.add(new JButton("Flow Button 2"));

            // 2. Panel with BorderLayout
            JPanel borderPanel = new JPanel(new BorderLayout());
            borderPanel.add(new JLabel("BorderLayout Panel (North)", JLabel.CENTER), BorderLayout.NORTH);
            borderPanel.add(new JButton("West"), BorderLayout.WEST);
            borderPanel.add(new JButton("Center"), BorderLayout.CENTER);
            borderPanel.add(new JButton("East"), BorderLayout.EAST);
            borderPanel.add(new JButton("South"), BorderLayout.SOUTH);

            // 3. Panel with GridLayout
            JPanel gridPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns
            gridPanel.add(new JLabel("Grid R1C1"));
            gridPanel.add(new JButton("Button R1C2"));
            gridPanel.add(new JLabel("Grid R2C1"));
            gridPanel.add(new JButton("Button R2C2"));
            gridPanel.add(new JLabel("Grid R3C1"));
            gridPanel.add(new JButton("Button R3C2"));

            // Add panels to main frame
            frame.add(flowPanel);
            frame.add(borderPanel);
            frame.add(gridPanel);

            // Position in center of screen and make visible
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
