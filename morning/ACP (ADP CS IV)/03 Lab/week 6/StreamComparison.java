import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Task 4: Compare different I/O stream classes.
 * 
 * This program generates a file and reads it using:
 * 1. Byte Streams (unbuffered: FileInputStream/FileOutputStream)
 * 2. Character Streams (unbuffered: FileReader/FileWriter)
 * 3. Buffered Byte Streams (BufferedInputStream/BufferedOutputStream)
 * 
 * Measures and compares the time taken for each method to demonstrate efficiency.
 */
public class StreamComparison {
    private static final String TEST_FILE = "stream_benchmark.dat";
    private static final int FILE_SIZE_BYTES = 500 * 1024; // 500 KB file

    public static void main(String[] args) {
        System.out.println("--- Stream Benchmarking and Comparison ---");
        System.out.println("Generating " + FILE_SIZE_BYTES / 1024 + " KB benchmark file...");
        createBenchmarkFile();
        
        System.out.println("\nExecuting read comparisons...");
        
        // 1. Unbuffered Byte Stream (FileInputStream)
        long start = System.currentTimeMillis();
        long bytesReadByteStream = readWithByteStream();
        long end = System.currentTimeMillis();
        long timeByteStream = end - start;
        System.out.println("FileInputStream (Unbuffered Byte Stream):");
        System.out.println("  Bytes Read: " + bytesReadByteStream + " bytes");
        System.out.println("  Time Taken: " + timeByteStream + " ms");
        
        // 2. Unbuffered Character Stream (FileReader)
        start = System.currentTimeMillis();
        long charsReadCharStream = readWithCharStream();
        end = System.currentTimeMillis();
        long timeCharStream = end - start;
        System.out.println("FileReader (Unbuffered Character Stream):");
        System.out.println("  Characters Read: " + charsReadCharStream + " chars");
        System.out.println("  Time Taken: " + timeCharStream + " ms");
        
        // 3. Buffered Byte Stream (BufferedInputStream)
        start = System.currentTimeMillis();
        long bytesReadBuffered = readWithBufferedStream();
        end = System.currentTimeMillis();
        long timeBuffered = end - start;
        System.out.println("BufferedInputStream (Buffered Byte Stream):");
        System.out.println("  Bytes Read: " + bytesReadBuffered + " bytes");
        System.out.println("  Time Taken: " + timeBuffered + " ms");
        
        // Cleanup benchmark file
        new File(TEST_FILE).delete();
        System.out.println("\nBenchmark finished. Temporary file deleted.");
    }
    
    private static void createBenchmarkFile() {
        try (FileOutputStream out = new FileOutputStream(TEST_FILE)) {
            byte[] data = new byte[1024]; // 1KB buffer
            // Fill with random characters
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) ('A' + (i % 26));
            }
            for (int i = 0; i < FILE_SIZE_BYTES / 1024; i++) {
                out.write(data);
            }
        } catch (IOException e) {
            System.out.println("Error generating test file: " + e.getMessage());
        }
    }
    
    private static long readWithByteStream() {
        long byteCount = 0;
        try (FileInputStream fis = new FileInputStream(TEST_FILE)) {
            int b;
            // Read byte by byte
            while ((b = fis.read()) != -1) {
                byteCount++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return byteCount;
    }
    
    private static long readWithCharStream() {
        long charCount = 0;
        try (FileReader fr = new FileReader(TEST_FILE)) {
            int c;
            // Read char by char
            while ((c = fr.read()) != -1) {
                charCount++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return charCount;
    }
    
    private static long readWithBufferedStream() {
        long byteCount = 0;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(TEST_FILE))) {
            byte[] buffer = new byte[8192]; // 8KB buffer size
            int bytesRead;
            // Read blocks of bytes
            while ((bytesRead = bis.read(buffer)) != -1) {
                byteCount += bytesRead;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return byteCount;
    }
}
