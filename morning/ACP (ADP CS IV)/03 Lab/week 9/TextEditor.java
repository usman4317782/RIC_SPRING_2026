import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Task 3: Build a simple text editor with file open/save functionality.
 */
public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile = null;

    public TextEditor() {
        setTitle("Simple Text Editor");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main editing text area
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Setup File Chooser starting at current directory
        fileChooser = new JFileChooser(new File("."));

        // Setup Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open File...");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Bind Menu Listeners
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile(false));
        saveAsItem.addActionListener(e -> saveFile(true));
        exitItem.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void openFile() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                currentFile = selectedFile;
                setTitle("Simple Text Editor - " + currentFile.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(),
                        "I/O Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile(boolean saveAs) {
        if (saveAs || currentFile == null) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return; // User cancelled
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(textArea.getText());
            setTitle("Simple Text Editor - " + currentFile.getName());
            JOptionPane.showMessageDialog(this, "File saved successfully!",
                    "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing file: " + ex.getMessage(),
                    "I/O Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new TextEditor().setVisible(true));
    }
}
