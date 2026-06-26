/**
 * Task 2: Create a program that uses various operators (arithmetic, relational, logical).
 */
public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 15;
        int b = 4;
        
        System.out.println("--- Arithmetic Operators ---");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Addition (a + b)        = " + (a + b));
        System.out.println("Subtraction (a - b)     = " + (a - b));
        System.out.println("Multiplication (a * b)  = " + (a * b));
        System.out.println("Division (a / b)        = " + (a / b));
        System.out.println("Modulo (a % b)          = " + (a % b));
        
        System.out.println("\n--- Relational Operators ---");
        System.out.println("a == b : " + (a == b));
        System.out.println("a != b : " + (a != b));
        System.out.println("a > b  : " + (a > b));
        System.out.println("a < b  : " + (a < b));
        System.out.println("a >= b : " + (a >= b));
        System.out.println("a <= b : " + (a <= b));
        
        System.out.println("\n--- Logical Operators ---");
        boolean condition1 = (a > b);  // true
        boolean condition2 = (b == 0); // false
        System.out.println("c1 = (a > b) is " + condition1);
        System.out.println("c2 = (b == 0) is " + condition2);
        System.out.println("Logical AND (c1 && c2) : " + (condition1 && condition2));
        System.out.println("Logical OR  (c1 || c2) : " + (condition1 || condition2));
        System.out.println("Logical NOT (!c1)      : " + (!condition1));
    }
}
