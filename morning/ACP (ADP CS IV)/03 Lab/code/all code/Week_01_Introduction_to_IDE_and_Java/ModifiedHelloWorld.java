/**
 * Lab Task 4: Experiment with basic Java syntax by modifying the Hello World program.
 * This version prints system information and greets a user if arguments are provided.
 * 
 * To compile: javac ModifiedHelloWorld.java
 * To run: java ModifiedHelloWorld [YourName]
 */
public class ModifiedHelloWorld {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("      MODIFIED HELLO WORLD PROGRAM       ");
        System.out.println("=========================================");
        
        // Conditional greeting
        if (args.length > 0) {
            System.out.println("Hello, " + args[0] + "!");
        } else {
            System.out.println("Hello, Guest! Pass your name as a command-line argument next time.");
        }
        
        // Print Java Version
        String javaVersion = System.getProperty("java.version");
        System.out.println("You are running Java Version: " + javaVersion);
        
        // Print OS Name
        String osName = System.getProperty("os.name");
        System.out.println("Operating System: " + osName);
        System.out.println("=========================================");
    }
}
