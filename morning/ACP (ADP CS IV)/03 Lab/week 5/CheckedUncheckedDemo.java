import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Task 3: Handle different types of exceptions (checked and unchecked).
 * 
 * Checked exceptions are checked at compile-time (e.g., IOException, FileNotFoundException).
 * Unchecked exceptions occur at runtime (e.g., NullPointerException, NumberFormatException).
 */
public class CheckedUncheckedDemo {
    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Handling Checked Exception (FileNotFoundException) ---");
        // The compiler FORCES us to handle checked exceptions (using try-catch or throws)
        try {
            System.out.println("Attempting to open non-existent file...");
            FileReader reader = new FileReader("non_existent_file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Caught Checked Exception: " + e.toString());
        }
        
        System.out.println("\n--- Scenario 2: Handling Unchecked Exception (NullPointerException) ---");
        // The compiler does NOT force us to handle unchecked exceptions, but they cause crash if not caught
        try {
            String text = null;
            System.out.println("Attempting to access text length...");
            int length = text.length(); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught Unchecked Exception: " + e.toString());
        }
        
        System.out.println("\n--- Scenario 3: Handling Unchecked Exception (NumberFormatException) ---");
        try {
            String invalidNum = "123abc";
            System.out.println("Attempting to parse invalid integer: " + invalidNum);
            int number = Integer.parseInt(invalidNum); // Throws NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Caught Unchecked Exception: " + e.toString());
        }
    }
}
