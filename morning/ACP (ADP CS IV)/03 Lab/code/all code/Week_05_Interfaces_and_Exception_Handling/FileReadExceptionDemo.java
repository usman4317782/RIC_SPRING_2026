import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lab Task 4: Implement a program that reads numbers from a file with proper exception handling.
 * 
 * To compile: javac FileReadExceptionDemo.java
 * To run: java FileReadExceptionDemo
 */
public class FileReadExceptionDemo {
    public static void main(String[] args) {
        String testFileName = "numbers_test.txt";

        // Let's create a sample file first with both valid numbers and a bad string to test parsing errors.
        createSampleFile(testFileName);

        System.out.println("----- Reading numbers from: " + testFileName + " -----");
        readNumbers(testFileName);

        // Test with a missing file to show FileNotFoundException handling
        System.out.println("\n----- Reading from a non-existent file -----");
        readNumbers("does_not_exist.txt");
    }

    private static void createSampleFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("10");
            writer.println("25");
            writer.println("abc"); // Invalid numeric input
            writer.println("42");
            System.out.println("Created test file '" + filename + "' with mixed numeric contents.");
        } catch (FileNotFoundException e) {
            System.err.println("Could not create test file: " + e.getMessage());
        }
    }

    private static void readNumbers(String filename) {
        File file = new File(filename);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            int lineNumber = 0;
            int sum = 0;
            
            while (fileScanner.hasNextLine()) {
                lineNumber++;
                String line = fileScanner.nextLine().trim();
                try {
                    int num = Integer.parseInt(line);
                    sum += num;
                    System.out.println("Line " + lineNumber + ": Successfully parsed number: " + num);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + lineNumber + ": Error: Cannot parse '" + line + "' as an integer. Skipping.");
                }
            }
            System.out.println("File processing completed. Sum of successfully parsed numbers: " + sum);

        } catch (FileNotFoundException e) {
            System.out.println("Error: The file '" + filename + "' was not found. Details: " + e.getMessage());
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
                System.out.println("File Scanner resource closed in finally block.");
            }
        }
    }
}
