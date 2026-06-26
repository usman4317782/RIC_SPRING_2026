import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Task 4: Build a GUI with multiple event listeners (mouse, keyboard, button).
 */
public class MultipleListenersGUI extends JFrame {
    private JTextArea eventLogger;
    private JButton actionButton;
    private JTextField keyInputField;
    private JPanel mouseInteractivePanel;

    public MultipleListenersGUI() {
        setTitle("Multi-Listener Event Console");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Left Panel containing controls
        JPanel controlsPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // 1. Button Control
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBorder(new TitledBorder("Button Listener"));
        actionButton = new JButton("Click Me!");
        btnPanel.add(actionButton);
        controlsPanel.add(btnPanel);

        // 2. Keyboard Control
        JPanel keyPanel = new JPanel(new BorderLayout());
        keyPanel.setBorder(new TitledBorder("Keyboard Listener (Type below)"));
        keyInputField = new JTextField();
        keyPanel.add(keyInputField, BorderLayout.CENTER);
        controlsPanel.add(keyPanel);

        // 3. Mouse Control Area
        mouseInteractivePanel = new JPanel(new BorderLayout());
        mouseInteractivePanel.setBorder(new TitledBorder("Mouse Listener Area"));
        JLabel mouseLabel = new JLabel("Hover or Click here", JLabel.CENTER);
        mouseInteractivePanel.add(mouseLabel, BorderLayout.CENTER);
        controlsPanel.add(mouseInteractivePanel);

        add(controlsPanel, BorderLayout.WEST);

        // Right side event log screen
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new TitledBorder("Real-time Event Logger Console"));
        eventLogger = new JTextArea();
        eventLogger.setEditable(false);
        logPanel.add(new JScrollPane(eventLogger), BorderLayout.CENTER);
        add(logPanel, BorderLayout.CENTER);

        // Register Action Listener on Button
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logEvent("[Button]: Click action performed.");
            }
        });

        // Register Key Listener on TextField
        keyInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                logEvent("[Keyboard]: Key Typed -> '" + e.getKeyChar() + "'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                logEvent("[Keyboard]: Key Pressed -> Code: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                logEvent("[Keyboard]: Key Released -> Code: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        // Register Mouse Listener on Interactive Panel
        mouseInteractivePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logEvent("[Mouse]: Clicked at coordinates (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                logEvent("[Mouse]: Pressed at coordinates (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                logEvent("[Mouse]: Released at coordinates (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logEvent("[Mouse]: Cursor Entered interactive panel area.");
                mouseInteractivePanel.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logEvent("[Mouse]: Cursor Exited interactive panel area.");
                mouseInteractivePanel.setBackground(null); // default background
            }
        });

        setLocationRelativeTo(null);
    }

    // Logging helper
    private void logEvent(String message) {
        eventLogger.append(message + "\n");
        // Scroll down to show latest log
        eventLogger.setCaretPosition(eventLogger.getDocument().getLength());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MultipleListenersGUI().setVisible(true);
            }
        });
    }
}
