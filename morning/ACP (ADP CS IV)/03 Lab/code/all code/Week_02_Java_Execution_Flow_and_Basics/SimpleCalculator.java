import java.util.Scanner;

/**
 * Lab Task 3: Develop a simple calculator program that takes two numbers and performs basic operations.
 * 
 * To compile: javac SimpleCalculator.java
 * To run: java SimpleCalculator
 */
public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("======================================");
        System.out.println("          SIMPLE CALCULATOR           ");
        System.out.println("======================================");
        
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.println("Choose operation: ");
        System.out.println(" + : Addition");
        System.out.println(" - : Subtraction");
        System.out.println(" * : Multiplication");
        System.out.println(" / : Division");
        System.out.print("Enter your choice (+, -, *, /): ");
        char operator = scanner.next().charAt(0);
        
        double result = 0;
        boolean valid = true;
        
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    valid = false;
                }
                break;
            default:
                System.out.println("Error: Invalid operator chosen.");
                valid = false;
                break;
        }
        
        if (valid) {
            System.out.println("======================================");
            System.out.printf("Result: %.2f %c %.2f = %.2f\n", num1, operator, num2, result);
            System.out.println("======================================");
        }
        
        scanner.close();
    }
}
