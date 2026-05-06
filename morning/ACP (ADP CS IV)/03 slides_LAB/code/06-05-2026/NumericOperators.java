/**
 * Concept: To use Java operators to write numeric expressions.
 */
public class NumericOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;

        // Basic Operators
        int sum = a + b;        // Addition
        int diff = a - b;       // Subtraction
        int product = a * b;    // Multiplication
        int quotient = a / b;   // Integer Division (removes decimal part)
        int remainder = a % b;  // Modulo (returns the remainder)

        System.out.println("Numbers: " + a + " and " + b);
        System.out.println("Addition (10 + 3): " + sum);
        System.out.println("Subtraction (10 - 3): " + diff);
        System.out.println("Multiplication (10 * 3): " + product);
        System.out.println("Integer Division (10 / 3): " + quotient);
        System.out.println("Modulo/Remainder (10 % 3): " + remainder);

        // Division with double for precision
        double realQuotient = (double) a / b;
        System.out.println("Real Division (10.0 / 3): " + realQuotient);
    }
}
