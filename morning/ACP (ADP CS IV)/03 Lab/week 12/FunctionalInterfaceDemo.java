/**
 * Task 2: Implement functional interfaces using lambdas.
 */

// Custom Functional Interface 1: String Operation
@FunctionalInterface
interface StringTransformer {
    // A functional interface has EXACTLY one abstract method
    String transform(String input);
}

// Custom Functional Interface 2: Numeric Operation
@FunctionalInterface
interface ArithmeticOperator {
    double execute(double operand1, double operand2);
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== Custom Functional Interfaces with Lambdas ===");

        // 1. Implementing StringTransformer using lambdas
        StringTransformer upperCase = (str) -> str.toUpperCase();
        StringTransformer reverse = (str) -> new StringBuilder(str).reverse().toString();
        StringTransformer whisper = (str) -> str.toLowerCase() + "... (whisper)";

        String text = "Advanced Computer Programming";
        System.out.println("Original String : " + text);
        System.out.println("Upper Case      : " + upperCase.transform(text));
        System.out.println("Reversed        : " + reverse.transform(text));
        System.out.println("Whispering      : " + whisper.transform(text));

        System.out.println();

        // 2. Implementing ArithmeticOperator using lambdas
        ArithmeticOperator addition = (x, y) -> x + y;
        ArithmeticOperator multiplication = (x, y) -> x * y;
        ArithmeticOperator division = (x, y) -> {
            if (y == 0) {
                System.out.println("Error: Div by 0");
                return 0;
            }
            return x / y;
        };

        double a = 12.5;
        double b = 4.0;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Addition (a + b)       = " + addition.execute(a, b));
        System.out.println("Multiplication (a * b) = " + multiplication.execute(a, b));
        System.out.println("Division (a / b)       = " + division.execute(a, b));
    }
}
