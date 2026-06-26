import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 * Task 2: Experiment with different layout managers (FlowLayout, BorderLayout, GridLayout).
 * 
 * This program displays three panels side-by-side showing how different layout managers 
 * arrange their components (buttons).
 */
public class LayoutManagersDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layout Managers Comparison Demo");
        frame.setSize(800, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Master layout: 1 row, 3 columns to compare the three layouts
        frame.setLayout(new GridLayout(1, 3, 10, 10)); 

        // Panel 1: FlowLayout
        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.setBorder(new TitledBorder("FlowLayout (Flows L-to-R, wraps)"));
        flowPanel.add(new JButton("Btn 1"));
        flowPanel.add(new JButton("Button 2"));
        flowPanel.add(new JButton("Long Button 3"));
        flowPanel.add(new JButton("Btn 4"));

        // Panel 2: BorderLayout
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(new TitledBorder("BorderLayout (N, S, E, W, Center)"));
        borderPanel.add(new JButton("North"), BorderLayout.NORTH);
        borderPanel.add(new JButton("South"), BorderLayout.SOUTH);
        borderPanel.add(new JButton("West"), BorderLayout.WEST);
        borderPanel.add(new JButton("East"), BorderLayout.EAST);
        borderPanel.add(new JButton("Center"), BorderLayout.CENTER);

        // Panel 3: GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns, gaps of 5
        gridPanel.setBorder(new TitledBorder("GridLayout (Equal sized grid cell layout)"));
        gridPanel.add(new JButton("Row 1, Col 1"));
        gridPanel.add(new JButton("Row 1, Col 2"));
        gridPanel.add(new JButton("Row 2, Col 1"));
        gridPanel.add(new JButton("Row 2, Col 2"));
        gridPanel.add(new JButton("Row 3, Col 1"));
        gridPanel.add(new JButton("Row 3, Col 2"));

        // Add the three panels to the frame
        frame.add(flowPanel);
        frame.add(borderPanel);
        frame.add(gridPanel);

        // Center on screen and show
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
