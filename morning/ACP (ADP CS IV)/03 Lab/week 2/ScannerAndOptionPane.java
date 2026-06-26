import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Task 4: Experiment with Scanner class for console input and JOptionPane for dialog input.
 */
public class ScannerAndOptionPane {
    public static void main(String[] args) {
        // Part 1: Console Input using Scanner
        System.out.println("--- Part 1: Scanner (Console Input) ---");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.println("Hello " + name + "! You are " + age + " years old.");
        System.out.println("Console input finished. Now displaying Dialog input...\n");
        
        // Part 2: Dialog Input using JOptionPane
        // Prompt for Name
        String dialogName = JOptionPane.showInputDialog(null, 
                "Enter your name (JOptionPane):", 
                "Input Dialog", 
                JOptionPane.QUESTION_MESSAGE);
                
        // Prompt for Age
        String ageStr = JOptionPane.showInputDialog(null, 
                "Enter your age (JOptionPane):", 
                "Input Dialog", 
                JOptionPane.QUESTION_MESSAGE);
        
        // Parse Age and display confirmation
        if (dialogName != null && ageStr != null) {
            try {
                int dialogAge = Integer.parseInt(ageStr);
                String msg = "Name: " + dialogName + "\nAge: " + dialogAge + "\nWelcome to the ACP Lab GUI!";
                JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid age format entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        scanner.close();
    }
}
