import javax.swing.JOptionPane;

/**
 * Concept: To obtain input using the JOptionPane input dialog boxes.
 */
public class JOptionPaneInput {
    public static void main(String[] args) {
        // Display an input dialog box and store the result in a String
        String name = JOptionPane.showInputDialog("Please enter your name:");

        // Display another input dialog box for age
        String ageString = JOptionPane.showInputDialog("Please enter your age:");
        
        // Since showInputDialog returns a String, we must convert it to an integer
        int age = Integer.parseInt(ageString);

        // Display the result in a message dialog box
        String message = "Welcome, " + name + "!\nYou are " + age + " years old.";
        JOptionPane.showMessageDialog(null, message);
    }
}
