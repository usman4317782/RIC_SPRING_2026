/**
 * Lab Task 2: Create a program that uses various operators (arithmetic, relational, logical).
 * 
 * To compile: javac OperatorsDemo.java
 * To run: java OperatorsDemo
 */
public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 15;
        int b = 4;
        
        System.out.println("----- Arithmetic Operators -----");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Addition (a + b):        " + (a + b));
        System.out.println("Subtraction (a - b):     " + (a - b));
        System.out.println("Multiplication (a * b):  " + (a * b));
        System.out.println("Division (a / b):        " + (a / b) + "  (Integer division)");
        System.out.println("Division (Double):       " + ((double)a / b));
        System.out.println("Modulo (a % b):          " + (a % b));
        
        System.out.println("\n----- Relational Operators -----");
        System.out.println("a == b:  " + (a == b));
        System.out.println("a != b:  " + (a != b));
        System.out.println("a > b:   " + (a > b));
        System.out.println("a < b:   " + (a < b));
        System.out.println("a >= b:  " + (a >= b));
        System.out.println("a <= b:  " + (a <= b));
        
        System.out.println("\n----- Logical Operators -----");
        boolean x = true;
        boolean y = false;
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("Logical AND (x && y): " + (x && y));
        System.out.println("Logical OR (x || y):  " + (x || y));
        System.out.println("Logical NOT (!x):     " + (!x));
        System.out.println("Logical NOT (!y):     " + (!y));
        
        System.out.println("\n----- Bitwise/Unary Operators -----");
        System.out.println("Increment (a++):      " + (a++) + " (Value is print-then-increment. Now a = " + a + ")");
        System.out.println("Pre-increment (++a):  " + (++a) + " (Value is increment-then-print)");
    }
}
