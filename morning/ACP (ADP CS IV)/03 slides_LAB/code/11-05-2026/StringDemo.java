public class StringDemo {
    public static void main(String[] args) {
        // Representing a string using the String type
        String greeting = "Hello, World!";
        
        // Creating a String object using the 'new' keyword
        String message = new String("Welcome to Java Programming");

        System.out.println("String Literal: " + greeting);
        System.out.println("String Object: " + message);

        // Common String methods
        System.out.println("Length of greeting: " + greeting.length());
        System.out.println("Uppercase: " + greeting.toUpperCase());
    }
}
