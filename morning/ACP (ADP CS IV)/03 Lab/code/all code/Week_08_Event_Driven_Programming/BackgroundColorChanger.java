import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * Lab Task 2: Create a program that changes background color based on user selection.
 * 
 * To compile: javac BackgroundColorChanger.java
 * To run: java BackgroundColorChanger
 */
public class BackgroundColorChanger extends JFrame {
    private JPanel colorPanel;
    private JComboBox<String> colorDropdown;
    private JRadioButton redBtn, greenBtn, blueBtn;

    public BackgroundColorChanger() {
        super("Background Color Changer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Center Panel whose background will change
        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);
        add(colorPanel, BorderLayout.CENTER);

        // Control Panel at South
        JPanel controlPanel = new JPanel(new FlowLayout());
        
        // 1. Dropdown Selector
        controlPanel.add(new JLabel("Dropdown:"));
        String[] colors = {"White", "Light Gray", "Yellow", "Orange", "Pink"};
        colorDropdown = new JComboBox<>(colors);
        controlPanel.add(colorDropdown);
        
        // 2. Radio Button Selector
        controlPanel.add(new JLabel(" | Radio Buttons:"));
        redBtn = new JRadioButton("Red");
        greenBtn = new JRadioButton("Green");
        blueBtn = new JRadioButton("Blue");
        
        ButtonGroup group = new ButtonGroup();
        group.add(redBtn);
        group.add(greenBtn);
        group.add(blueBtn);
        
        controlPanel.add(redBtn);
        controlPanel.add(greenBtn);
        controlPanel.add(blueBtn);

        add(controlPanel, BorderLayout.SOUTH);

        // Action listener for JComboBox dropdown
        colorDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) colorDropdown.getSelectedItem();
                group.clearSelection(); // Deselect radio buttons
                
                switch (selected) {
                    case "White": colorPanel.setBackground(Color.WHITE); break;
                    case "Light Gray": colorPanel.setBackground(Color.LIGHT_GRAY); break;
                    case "Yellow": colorPanel.setBackground(Color.YELLOW); break;
                    case "Orange": colorPanel.setBackground(Color.ORANGE); break;
                    case "Pink": colorPanel.setBackground(Color.PINK); break;
                }
            }
        });

        // Shared action listener for Radio Buttons
        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (redBtn.isSelected()) colorPanel.setBackground(new Color(255, 100, 100)); // Pastel red
                else if (greenBtn.isSelected()) colorPanel.setBackground(new Color(100, 255, 100)); // Pastel green
                else if (blueBtn.isSelected()) colorPanel.setBackground(new Color(100, 100, 255)); // Pastel blue
            }
        };

        redBtn.addActionListener(radioListener);
        greenBtn.addActionListener(radioListener);
        blueBtn.addActionListener(radioListener);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BackgroundColorChanger().setVisible(true);
        });
    }
}
