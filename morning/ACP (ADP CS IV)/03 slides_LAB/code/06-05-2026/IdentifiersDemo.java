/**
 * Concept: To use identifiers to name variables, constants, methods, and classes.
 * 
 * Rules for Identifiers:
 * 1. Must start with a letter, underscore (_), or dollar sign ($).
 * 2. Subsequent characters can be letters, digits, underscores, or dollar signs.
 * 3. Cannot be a Java keyword (like 'class', 'public', 'static').
 * 4. Case-sensitive.
 */
public class IdentifiersDemo {
    public static void main(String[] args) {
        // Valid Identifiers
        int myAge = 25;           // Camel Case (Standard for variables)
        double $price = 99.99;    // Can start with $
        int _score = 100;         // Can start with _
        String user_name = "Joe"; // Underscores are valid

        // Invalid Identifiers (commented out to avoid compilation errors)
        // int 2age = 10;         // Error: Cannot start with a digit
        // int class = 5;         // Error: Cannot use a Java keyword
        // int my-age = 30;       // Error: Hyphens are not allowed

        System.out.println("Valid Identifiers used:");
        System.out.println("myAge: " + myAge);
        System.out.println("$price: " + $price);
        System.out.println("_score: " + _score);
        System.out.println("user_name: " + user_name);
    }
}
