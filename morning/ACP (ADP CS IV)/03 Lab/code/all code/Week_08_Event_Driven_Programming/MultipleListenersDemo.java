import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Lab Task 4: Build a GUI with multiple event listeners (mouse, keyboard, button).
 * 
 * To compile: javac MultipleListenersDemo.java
 * To run: java MultipleListenersDemo
 */
public class MultipleListenersDemo extends JFrame {
    private JLabel statusLabel;
    private JButton actionButton;
    private JPanel mousePanel;
    private JTextField keyField;

    public MultipleListenersDemo() {
        super("Multiple Event Listeners Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLayout(new BorderLayout(10, 10));

        // 1. Status Label at the top
        statusLabel = new JLabel("Status: Interact with elements below", JLabel.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        // Grid Panel for the three interaction targets
        JPanel interactionPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        interactionPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // Target A: Action Listener (Button)
        JPanel btnWrapper = new JPanel(new BorderLayout());
        btnWrapper.setBorder(BorderFactory.createTitledBorder("1. Button (ActionListener)"));
        actionButton = new JButton("Click Me!");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Status: Button was clicked!");
            }
        });
        btnWrapper.add(actionButton, BorderLayout.CENTER);
        interactionPanel.add(btnWrapper);

        // Target B: Mouse Listener (Panel)
        mousePanel = new JPanel(new BorderLayout());
        mousePanel.setBackground(Color.LIGHT_GRAY);
        mousePanel.setBorder(BorderFactory.createTitledBorder("2. Mouse Area (MouseListener)"));
        JLabel mouseLabel = new JLabel("Hover or click inside this box", JLabel.CENTER);
        mousePanel.add(mouseLabel, BorderLayout.CENTER);
        
        mousePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statusLabel.setText("Status: Mouse clicked at [" + e.getX() + ", " + e.getY() + "]");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                mousePanel.setBackground(Color.YELLOW);
                statusLabel.setText("Status: Mouse entered the panel area");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePanel.setBackground(Color.LIGHT_GRAY);
                statusLabel.setText("Status: Mouse exited the panel area");
            }
        });
        interactionPanel.add(mousePanel);

        // Target C: Key Listener (Text Field)
        JPanel keyWrapper = new JPanel(new BorderLayout());
        keyWrapper.setBorder(BorderFactory.createTitledBorder("3. Text Field (KeyListener)"));
        keyField = new JTextField();
        keyField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                statusLabel.setText("Status: Key Typed: '" + e.getKeyChar() + "'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Displays special keys like Shift, Enter, Backspace
                statusLabel.setText("Status: Key Pressed code: " + e.getKeyCode() + " (" + KeyEvent.getKeyText(e.getKeyCode()) + ")");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                statusLabel.setText("Status: Key Released");
            }
        });
        keyWrapper.add(keyField, BorderLayout.CENTER);
        interactionPanel.add(keyWrapper);

        add(interactionPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MultipleListenersDemo().setVisible(true);
        });
    }
}
