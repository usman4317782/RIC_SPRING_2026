/**
 * Lab Task 1: Write programs demonstrating try-catch-finally blocks.
 * Shows execution path when exceptions do and do not occur, and how finally always runs.
 * 
 * To compile: javac TryCatchFinallyDemo.java
 * To run: java TryCatchFinallyDemo
 */
public class TryCatchFinallyDemo {
    public static void main(String[] args) {
        System.out.println("----- Case 1: Exception Occurs -----");
        divideAndDemonstrate(10, 0);

        System.out.println("\n----- Case 2: No Exception Occurs -----");
        divideAndDemonstrate(10, 2);
    }

    private static void divideAndDemonstrate(int num, int denom) {
        try {
            System.out.println("Inside TRY block: Attempting division (" + num + " / " + denom + ")");
            int result = num / denom;
            System.out.println("TRY block completed. Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Inside CATCH block: Handled exception! Details: " + e.getMessage());
        } finally {
            System.out.println("Inside FINALLY block: This block always executes.");
        }
        System.out.println("Execution continues outside the try-catch-finally structure.");
    }
}
