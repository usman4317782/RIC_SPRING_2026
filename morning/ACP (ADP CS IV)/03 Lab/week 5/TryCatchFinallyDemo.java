/**
 * Task 1: Write programs demonstrating try-catch-finally blocks.
 */
public class TryCatchFinallyDemo {
    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Handling ArithmeticException ---");
        try {
            int numerator = 10;
            int denominator = 0;
            System.out.println("Attempting division: " + numerator + " / " + denominator);
            int result = numerator / denominator;
            System.out.println("Division successful. Result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Catch block: Exception caught -> " + e.getMessage());
        } finally {
            System.out.println("Finally block: This block always executes regardless of exceptions.");
        }
        
        System.out.println("\n--- Scenario 2: Handling ArrayIndexOutOfBoundsException ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Attempting to access array element at index 5...");
            int value = numbers[5];
            System.out.println("Element value = " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Catch block: Exception caught -> " + e.getMessage());
        } finally {
            System.out.println("Finally block: Clean-up logic here (always executes).");
        }
    }
}
