/**
 * Task 1: Write a program that demonstrates different primitive data types.
 * 
 * Demonstrates variables, size, range, and output of byte, short, int, long,
 * float, double, char, and boolean.
 */
public class PrimitiveDataTypes {
    public static void main(String[] args) {
        // Integer types
        byte byteVal = 127; // Max byte value
        short shortVal = 32767; // Max short value
        int intVal = 2147483647; // Max int value
        long longVal = 9223372036854775807L; // Max long value
        
        // Floating point types
        float floatVal = 3.14159f;
        double doubleVal = 3.141592653589793;
        
        // Character type
        char charVal = 'A';
        
        // Boolean type
        boolean booleanVal = true;
        
        // Displaying details
        System.out.println("--- Java Primitive Data Types ---");
        System.out.println("byte   : Value = " + byteVal + " (Size: 8 bits, Range: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE + ")");
        System.out.println("short  : Value = " + shortVal + " (Size: 16 bits, Range: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE + ")");
        System.out.println("int    : Value = " + intVal + " (Size: 32 bits, Range: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + ")");
        System.out.println("long   : Value = " + longVal + " (Size: 64 bits, Range: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE + ")");
        System.out.println("float  : Value = " + floatVal + " (Size: 32 bits, Range: " + Float.MIN_VALUE + " to " + Float.MAX_VALUE + ")");
        System.out.println("double : Value = " + doubleVal + " (Size: 64 bits, Range: " + Double.MIN_VALUE + " to " + Double.MAX_VALUE + ")");
        System.out.println("char   : Value = " + charVal + " (Size: 16 bits, Unicode character)");
        System.out.println("boolean: Value = " + booleanVal + " (Size: 1 bit, true/false)");
    }
}
