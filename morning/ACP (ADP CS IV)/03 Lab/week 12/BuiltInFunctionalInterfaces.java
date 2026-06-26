import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Date;
import java.util.Random;

/**
 * Task 3: Use lambda expressions with Java's built-in functional interfaces.
 * 
 * Demonstrates Predicate, Consumer, Function, and Supplier from java.util.function package.
 */
public class BuiltInFunctionalInterfaces {
    public static void main(String[] args) {
        System.out.println("=== Built-In Functional Interfaces Demo ===");

        // 1. Predicate<T> (Evaluates a boolean condition)
        // Checks if an integer is even
        Predicate<Integer> isEven = (num) -> num % 2 == 0;
        System.out.println("--- Predicate Demo ---");
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 15 even? " + isEven.test(15));
        
        // 2. Consumer<T> (Accepts single input, returns void / performs side-effect)
        // Prints a string in a decorated formatting
        Consumer<String> printer = (msg) -> System.out.println("[Log Console]: " + msg);
        System.out.println("\n--- Consumer Demo ---");
        printer.accept("Database transaction initialized.");
        printer.accept("Job executed successfully.");

        // 3. Function<T, R> (Transforms an input type T to return type R)
        // Takes a String representation of an integer, doubles it, and returns it as a String
        Function<String, Integer> parseAndDouble = (str) -> Integer.parseInt(str) * 2;
        System.out.println("\n--- Function Demo ---");
        String inputStr = "25";
        int doubledResult = parseAndDouble.apply(inputStr);
        System.out.println("Input String: \"" + inputStr + "\" -> Parsed & Doubled Result: " + doubledResult);

        // 4. Supplier<T> (Takes no input parameters, returns an object of type T)
        // Generates random security pin codes or timestamps
        Supplier<String> pinCodeSupplier = () -> {
            Random rand = new Random();
            int pin = 1000 + rand.nextInt(9000); // 4-digit PIN
            return "PIN-" + pin;
        };
        System.out.println("\n--- Supplier Demo ---");
        System.out.println("Generated Security Token 1: " + pinCodeSupplier.get());
        System.out.println("Generated Security Token 2: " + pinCodeSupplier.get());
        
        Supplier<Date> dateSupplier = () -> new Date();
        System.out.println("Current Date-Time Supplied:  " + dateSupplier.get());
    }
}
