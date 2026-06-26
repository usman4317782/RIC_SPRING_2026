import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Task 2: Create a program that changes background color based on user selection.
 * 
 * Uses premium, soft, modern color palettes rather than harsh primary colors.
 */
public class ColorChangerGUI extends JFrame {
    private JPanel colorPanel;
    private JComboBox<String> colorDropdown;
    private JLabel infoLabel;

    // Modern color choices (Name, Hex)
    private static final String[] COLOR_NAMES = {
        "Default Slate", "Sunset Peach", "Sky Blue", "Mint Green", "Deep Charcoal", "Soft Lavender"
    };

    private static final Color[] COLORS = {
        new Color(0xE2E8F0), // Slate Light
        new Color(0xFFD3B6), // Sunset Peach
        new Color(0xA8DADC), // Sky Blue
        new Color(0xD4EDDA), // Mint Green
        new Color(0x2D3748), // Deep Charcoal
        new Color(0xE8DAEF)  // Soft Lavender
    };

    public ColorChangerGUI() {
        setTitle("Theme & Background Color Changer");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Dropdown selection container at top
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Theme Color: "));
        
        colorDropdown = new JComboBox<>(COLOR_NAMES);
        controlPanel.add(colorDropdown);
        add(controlPanel, BorderLayout.NORTH);

        // Center Panel whose background will change
        colorPanel = new JPanel(new BorderLayout());
        colorPanel.setBackground(COLORS[0]); // Initial default color
        
        infoLabel = new JLabel("Theme: Slate Light", JLabel.CENTER);
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        infoLabel.setForeground(new Color(0x4A5568));
        colorPanel.add(infoLabel, BorderLayout.CENTER);
        
        add(colorPanel, BorderLayout.CENTER);

        // Item listener to change color on selection
        colorDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int selectedIndex = colorDropdown.getSelectedIndex();
                    Color selectedColor = COLORS[selectedIndex];
                    String selectedName = COLOR_NAMES[selectedIndex];

                    // Set background
                    colorPanel.setBackground(selectedColor);
                    infoLabel.setText("Theme: " + selectedName);

                    // Adjust text color based on background brightness
                    if (selectedName.equals("Deep Charcoal")) {
                        infoLabel.setForeground(Color.WHITE);
                    } else {
                        infoLabel.setForeground(new Color(0x4A5568));
                    }
                }
            }
        });

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ColorChangerGUI().setVisible(true);
            }
        });
    }
}
