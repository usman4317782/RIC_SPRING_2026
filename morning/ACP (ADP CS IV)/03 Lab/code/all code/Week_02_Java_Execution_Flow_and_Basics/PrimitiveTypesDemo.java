/**
 * Lab Task 1: Write a program that demonstrates different primitive data types.
 * 
 * To compile: javac PrimitiveTypesDemo.java
 * To run: java PrimitiveTypesDemo
 */
public class PrimitiveTypesDemo {
    public static void main(String[] args) {
        // Integer types
        byte byteVal = 127;                // 8-bit signed
        short shortVal = 32767;            // 16-bit signed
        int intVal = 2147483647;          // 32-bit signed
        long longVal = 9223372036854775807L; // 64-bit signed (note 'L' suffix)
        
        // Floating point types
        float floatVal = 3.14159f;        // 32-bit single-precision (note 'f' suffix)
        double doubleVal = 3.141592653589793; // 64-bit double-precision
        
        // Character type
        char charVal = 'A';               // 16-bit Unicode
        
        // Boolean type
        boolean booleanVal = true;         // true or false
        
        // Output values
        System.out.println("----- Java Primitive Data Types Demo -----");
        System.out.println("byte value:    " + byteVal + " (Size: 1 byte, Range: -128 to 127)");
        System.out.println("short value:   " + shortVal + " (Size: 2 bytes, Range: -32,768 to 32,767)");
        System.out.println("int value:     " + intVal + " (Size: 4 bytes)");
        System.out.println("long value:    " + longVal + " (Size: 8 bytes)");
        System.out.println("float value:   " + floatVal + " (Size: 4 bytes, single-precision)");
        System.out.println("double value:  " + doubleVal + " (Size: 8 bytes, double-precision)");
        System.out.println("char value:    " + charVal + " (Size: 2 bytes, character)");
        System.out.println("boolean value: " + booleanVal + " (Size: 1 bit conceptually, stores true/false)");
    }
}
