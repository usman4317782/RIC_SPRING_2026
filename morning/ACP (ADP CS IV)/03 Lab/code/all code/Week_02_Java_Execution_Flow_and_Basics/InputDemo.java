import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Lab Task 4: Experiment with Scanner class for console input and JOptionPane for dialog input.
 * 
 * To compile: javac InputDemo.java
 * To run: java InputDemo
 */
public class InputDemo {
    public static void main(String[] args) {
        System.out.println("----- Phase 1: Console Input using Scanner -----");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name (Console): ");
        String consoleName = scanner.nextLine();
        
        System.out.print("Enter your age (Console): ");
        int consoleAge = scanner.nextInt();
        
        System.out.println("Console Greeting: Hello, " + consoleName + "! You are " + consoleAge + " years old.");
        System.out.println("-----------------------------------------------\n");
        
        System.out.println("----- Phase 2: Dialog Input using JOptionPane -----");
        // Pop-up input dialog
        String dialogName = JOptionPane.showInputDialog(null, "What is your name?", "Input Name", JOptionPane.QUESTION_MESSAGE);
        String dialogAgeStr = JOptionPane.showInputDialog(null, "How old are you?", "Input Age", JOptionPane.QUESTION_MESSAGE);
        
        int dialogAge = 0;
        try {
            dialogAge = Integer.parseInt(dialogAgeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age input. Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            scanner.close();
            return;
        }
        
        // Pop-up message dialog
        String message = "Dialog Greeting: Hello, " + dialogName + "!\nIn dialog terms, you are " + dialogAge + " years old.";
        JOptionPane.showMessageDialog(null, message, "Greeting", JOptionPane.INFORMATION_MESSAGE);
        
        System.out.println("Dialog Greeting displayed successfully!");
        scanner.close();
    }
}
