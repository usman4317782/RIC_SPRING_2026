import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lab Task 1: Write a program to read from and write to text files.
 * Uses BufferedReader and BufferedWriter for efficient character-stream access.
 * 
 * To compile: javac TextFileReadWriteDemo.java
 * To run: java TextFileReadWriteDemo
 */
public class TextFileReadWriteDemo {
    public static void main(String[] args) {
        String filename = "sample_text.txt";
        
        System.out.println("----- Phase 1: Writing to Text File -----");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Hello, Java I/O Streams!\n");
            writer.write("This is a demonstration of writing text lines using BufferedWriter.\n");
            writer.write("BufferedWriter is buffered for efficient writing.\n");
            writer.write("Last line of sample file.\n");
            System.out.println("Text written successfully to '" + filename + "'");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("\n----- Phase 2: Reading from Text File -----");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNum + ": " + line);
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
