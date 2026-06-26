import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Task 1: Write a program to read from and write to text files.
 */
public class TextFileIO {
    private static final String TEXT_FILE = "sample_io_text.txt";

    public static void main(String[] args) {
        // Step 1: Write text to a file using BufferedWriter
        System.out.println("--- Writing to File: " + TEXT_FILE + " ---");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE))) {
            writer.write("Line 1: Advanced Computer Programming (ACP) Lab\n");
            writer.write("Line 2: Java I/O Streams and File Handling\n");
            writer.write("Line 3: Demonstrating text file read and write operations.\n");
            System.out.println("Text written successfully to the file.");
        } catch (IOException e) {
            System.out.println("Writing error occurred: " + e.getMessage());
        }
        
        System.out.println();
        
        // Step 2: Read text from the file using BufferedReader
        System.out.println("--- Reading from File: " + TEXT_FILE + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Row " + lineNumber + ": " + line);
                lineNumber++;
            }
            System.out.println("Finished reading file.");
        } catch (IOException e) {
            System.out.println("Reading error occurred: " + e.getMessage());
        }
    }
}
