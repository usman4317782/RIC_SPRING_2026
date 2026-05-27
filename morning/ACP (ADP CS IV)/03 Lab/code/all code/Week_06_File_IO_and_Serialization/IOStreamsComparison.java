import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lab Task 4: Compare different I/O stream classes.
 * Specifically demonstrates:
 * 1. Byte Stream (FileInputStream / FileOutputStream) - handles raw binary data (8-bit bytes).
 * 2. Character Stream (FileReader / FileWriter) - handles character data (16-bit Unicode characters).
 * 
 * To compile: javac IOStreamsComparison.java
 * To run: java IOStreamsComparison
 */
public class IOStreamsComparison {
    public static void main(String[] args) {
        String byteFile = "output_byte.txt";
        String charFile = "output_char.txt";
        
        // Unicode text containing non-ASCII symbols (e.g. math symbols and emojis)
        String sampleText = "Java I/O: ☕ + Code = ⚡ (Unicode symbols check: α, β, γ)";

        System.out.println("----- Phase 1: Writing Data -----");
        
        // 1. Write using Byte Stream (requires converting string to bytes)
        try (FileOutputStream byteOut = new FileOutputStream(byteFile)) {
            byte[] bytes = sampleText.getBytes("UTF-8");
            byteOut.write(bytes);
            System.out.println("Byte Stream: Wrote " + bytes.length + " raw bytes to " + byteFile);
        } catch (IOException e) {
            System.err.println("Byte Stream Write Error: " + e.getMessage());
        }

        // 2. Write using Character Stream (writes characters directly)
        try (FileWriter charWriter = new FileWriter(charFile)) {
            charWriter.write(sampleText);
            System.out.println("Character Stream: Wrote characters directly to " + charFile);
        } catch (IOException e) {
            System.err.println("Character Stream Write Error: " + e.getMessage());
        }

        System.out.println("\n----- Phase 2: Reading & Comparison -----");

        // 1. Read byte-by-byte using Byte Stream
        System.out.println("\nReading via Byte Stream (byte-by-byte):");
        try (FileInputStream byteIn = new FileInputStream(byteFile)) {
            int data;
            while ((data = byteIn.read()) != -1) {
                // Cast to char - note that multi-byte Unicode characters will be garbled when read byte-by-byte!
                System.out.print((char) data);
            }
            System.out.println();
            System.out.println("Note: Emojis and greek letters look garbled because casting a single raw byte to char breaks multibyte characters.");
        } catch (IOException e) {
            System.err.println("Byte Stream Read Error: " + e.getMessage());
        }

        // 2. Read char-by-char using Character Stream
        System.out.println("\nReading via Character Stream (char-by-char):");
        try (FileReader charReader = new FileReader(charFile)) {
            int data;
            while ((data = charReader.read()) != -1) {
                // Character streams correctly decode characters based on default system/file encoding
                System.out.print((char) data);
            }
            System.out.println();
            System.out.println("Note: Character stream reads and outputs Unicode correctly!");
        } catch (IOException e) {
            System.err.println("Character Stream Read Error: " + e.getMessage());
        }
    }
}
