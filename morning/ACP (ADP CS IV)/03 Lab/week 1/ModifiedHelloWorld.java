/**
 * Task 4: Experiment with basic Java syntax by modifying the Hello World program.
 * 
 * This program demonstrates variables, basic arithmetic operations,
 * string concatenation, and comments in Java.
 */
public class ModifiedHelloWorld {
    public static void main(String[] args) {
        // String variable storing a name
        String name = "Advanced Computer Programming student";
        
        // Integer variable for the lab year
        int year = 2026;
        
        // Printing greeting message using variable concatenation
        System.out.println("Hello, " + name + "!");
        System.out.println("Welcome to the ACP Lab of " + year + ".");
        
        // Basic arithmetic calculation
        int weekNumber = 1;
        int remainingWeeks = 16 - weekNumber;
        
        System.out.println("This is Week " + weekNumber + ".");
        System.out.println("You have " + remainingWeeks + " weeks left of lab topics.");
        
        // Example of multi-line comments
        /*
         * Java compilation compiles .java files into .class bytecodes,
         * which are then run by the JVM (Java Virtual Machine).
         */
    }
}
