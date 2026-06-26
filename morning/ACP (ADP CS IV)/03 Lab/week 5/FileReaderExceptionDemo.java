import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Task 4: Implement a program that reads numbers from a file with proper exception handling.
 */
public class FileReaderExceptionDemo {
    private static final String FILE_NAME = "numbers.txt";

    public static void main(String[] args) {
        // Step 1: Create a numbers file with some valid numbers and one invalid data entry to demonstrate parsing exception handling
        createSampleFile();

        System.out.println("--- Reading Numbers from file '" + FILE_NAME + "' ---");
        
        BufferedReader reader = null;
        double sum = 0;
        int count = 0;
        
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                try {
                    // Convert line string into double value
                    double val = Double.parseDouble(line);
                    sum += val;
                    count++;
                    System.out.println("Parsed number: " + val);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException: Failed to parse line '" + line + "' -> " + e.getMessage());
                }
            }
            
            if (count > 0) {
                System.out.println("\nSuccessfully read " + count + " numbers.");
                System.out.println("Sum of parsed numbers: " + sum);
                System.out.println("Average of parsed numbers: " + (sum / count));
            } else {
                System.out.println("No valid numbers were found in the file.");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: The file '" + FILE_NAME + "' could not be found -> " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: An error occurred while reading from the file -> " + e.getMessage());
        } finally {
            // Clean up: close resource stream
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("File reader closed successfully.");
                }
            } catch (IOException e) {
                System.out.println("Error while closing the file reader -> " + e.getMessage());
            }
        }
    }
    
    // Helper method to create a sample file
    private static void createSampleFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("10.5\n");
            writer.write("20.0\n");
            writer.write("invalid_number_here\n"); // Will cause parsing error
            writer.write("35.75\n");
            writer.write(" 50 \n");
            System.out.println("Sample file '" + FILE_NAME + "' created for demonstration.");
        } catch (IOException e) {
            System.out.println("Could not create sample file -> " + e.getMessage());
        }
    }
}
