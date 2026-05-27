import javax.swing.*;
import java.awt.*;
import java.util.function.*;

/**
 * Lab Task 4: Create a program that uses lambda expressions for event handling.
 * Demonstrates replacing verbose anonymous inner class ActionListeners with lambdas.
 *
 * To compile: javac LambdaEventHandlingDemo.java
 * To run:     java LambdaEventHandlingDemo
 */
public class LambdaEventHandlingDemo extends JFrame {

    private JTextField txtInput;
    private JTextArea  txtOutput;
    private int        clickCount = 0;

    public LambdaEventHandlingDemo() {
        setTitle("Lambda Event Handling - ACP Week 12");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout(8, 8));

        // ---- Top Input Panel ----
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        topPanel.add(new JLabel("Input:"));
        txtInput = new JTextField(22);
        topPanel.add(txtInput);
        add(topPanel, BorderLayout.NORTH);

        // ---- Output Area ----
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtOutput.setBackground(new Color(30, 30, 30));
        txtOutput.setForeground(new Color(180, 255, 180));
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        // ---- Button Panel with LAMBDA event handlers ----
        JPanel btnPanel = new JPanel(new GridLayout(2, 4, 6, 6));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(4, 8, 8, 8));

        // Helper: create a button with a lambda ActionListener
        createButton(btnPanel, "UPPERCASE",     e -> transformText(String::toUpperCase));
        createButton(btnPanel, "lowercase",     e -> transformText(String::toLowerCase));
        createButton(btnPanel, "Reverse",       e -> transformText(s -> new StringBuilder(s).reverse().toString()));
        createButton(btnPanel, "Count Words",   e -> {
            String t = txtInput.getText().trim();
            long count = t.isEmpty() ? 0 : java.util.Arrays.stream(t.split("\\s+")).count();
            log("Word count: " + count);
        });
        createButton(btnPanel, "Click Counter", e -> log("Button clicked " + (++clickCount) + " time(s)!"));
        createButton(btnPanel, "Is Palindrome", e -> {
            String t = txtInput.getText().trim().toLowerCase().replaceAll("\\s", "");
            String rev = new StringBuilder(t).reverse().toString();
            log("\"" + txtInput.getText().trim() + "\" is " + (t.equals(rev) ? "" : "NOT ") + "a palindrome.");
        });
        createButton(btnPanel, "Clear Output",  e -> txtOutput.setText(""));
        createButton(btnPanel, "Clear Input",   e -> { txtInput.setText(""); txtInput.requestFocus(); });

        add(btnPanel, BorderLayout.SOUTH);

        // ---- Mouse listener on output area (lambda) ----
        txtOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            // Lambda cannot directly implement MouseAdapter multi-method interface,
            // but we use method references to demonstrate closeness to lambdas
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lblStatus("Hover: Output area active.");
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                lblStatus("Ready.");
            }
        });

        // ---- Key listener using lambda-style on input field ----
        txtInput.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                int len = txtInput.getText().length();
                lblStatus("Characters typed: " + len);
            }
        });

        log("Lambda Event Handling Demo ready. Type something in the Input box and click a button!");
    }

    /** Creates a JButton with a lambda ActionListener and adds it to the panel */
    private void createButton(JPanel panel, String label, java.awt.event.ActionListener handler) {
        JButton btn = new JButton(label);
        btn.addActionListener(handler);  // <-- Lambda passed here
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        panel.add(btn);
    }

    private void transformText(Function<String, String> transformer) {
        String input = txtInput.getText();
        if (input.isEmpty()) { log("(Input is empty - nothing to transform)"); return; }
        String result = transformer.apply(input);
        log("Input:  \"" + input + "\"");
        log("Result: \"" + result + "\"");
    }

    private void log(String msg) {
        txtOutput.append(msg + "\n");
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
    }

    private void lblStatus(String msg) {
        setTitle("Lambda Event Handling - " + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LambdaEventHandlingDemo().setVisible(true));
    }
}
