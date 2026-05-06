import java.util.Scanner;


public class ScannerInput {
    public static void main(String[] args) {
        // Create a Scanner object to read from standard input (console)
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine(); // Read a string

        System.out.print("Enter your age: ");
        int age = input.nextInt(); // Read an integer

        System.out.print("Enter your weight (e.g., 65.5): ");
        double weight = input.nextDouble(); // Read a double

        // Output the collected information
        System.out.println("\n--- User Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weight + " kg");

        // Close the scanner
        input.close();
    }
}
