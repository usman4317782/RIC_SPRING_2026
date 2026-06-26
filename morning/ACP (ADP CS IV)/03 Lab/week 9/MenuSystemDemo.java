import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Task 2: Implement a menu system with file operations.
 */
public class MenuSystemDemo extends JFrame {
    public MenuSystemDemo() {
        setTitle("Menu System Demo");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status Label to display which action was clicked
        JLabel statusLabel = new JLabel("Click any menu item to test operations.", JLabel.CENTER);
        add(statusLabel, BorderLayout.CENTER);

        // 1. Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // 2. Create the "File" Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt+F shortcut

        // Create File menu items
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        
        JMenuItem openItem = new JMenuItem("Open File...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

        // Add items to File Menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Add a dividing line
        fileMenu.add(exitItem);

        // 3. Create the "Edit" Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Add Menus to Menu Bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Attach Menu Bar to the Frame
        setJMenuBar(menuBar);

        // Define Action Listeners
        newItem.addActionListener(e -> statusLabel.setText("Status: 'New File' operation triggered."));
        openItem.addActionListener(e -> {
            statusLabel.setText("Status: 'Open File' operation triggered.");
            JOptionPane.showMessageDialog(this, "Simulating File Open Dialog...", "Open", JOptionPane.INFORMATION_MESSAGE);
        });
        saveItem.addActionListener(e -> {
            statusLabel.setText("Status: 'Save File' operation triggered.");
            JOptionPane.showMessageDialog(this, "Simulating File Save operation...", "Save", JOptionPane.INFORMATION_MESSAGE);
        });
        exitItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Edit operations
        cutItem.addActionListener(e -> statusLabel.setText("Status: Cut selected."));
        copyItem.addActionListener(e -> statusLabel.setText("Status: Copy selected."));
        pasteItem.addActionListener(e -> statusLabel.setText("Status: Paste selected."));

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new MenuSystemDemo().setVisible(true));
    }
}
