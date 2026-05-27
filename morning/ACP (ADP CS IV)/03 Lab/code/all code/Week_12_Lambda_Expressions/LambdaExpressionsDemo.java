import java.util.*;
import java.util.function.*;

/**
 * Lab Task 1: Convert anonymous inner classes to lambda expressions.
 * Lab Task 2: Implement functional interfaces using lambdas.
 * Lab Task 3: Use lambda expressions with Java's built-in functional interfaces.
 *
 * To compile: javac LambdaExpressionsDemo.java
 * To run:     java LambdaExpressionsDemo
 */
public class LambdaExpressionsDemo {

    // ---- Custom Functional Interface ----
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    @FunctionalInterface
    interface Greeting {
        String greet(String name);
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T value);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Lambda Expressions Demo - ACP Week 12 ");
        System.out.println("========================================\n");

        // ---- Task 1: Anonymous Inner Class vs Lambda ----
        System.out.println("--- Task 1: Anonymous Inner Class → Lambda ---");

        // OLD WAY: Anonymous inner class
        Comparator<String> oldComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };

        // NEW WAY: Lambda expression
        Comparator<String> lambdaComparator = (s1, s2) -> s1.length() - s2.length();

        List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "Diana"));
        System.out.println("Before sorting: " + names);
        names.sort(lambdaComparator);
        System.out.println("After sorting by length (lambda): " + names);

        // Runnable: old vs lambda
        Runnable oldRunnable = new Runnable() {
            @Override public void run() { System.out.println("Old Runnable (anonymous class)"); }
        };
        Runnable lambdaRunnable = () -> System.out.println("New Runnable (lambda)");
        oldRunnable.run();
        lambdaRunnable.run();

        // ---- Task 2: Custom Functional Interfaces ----
        System.out.println("\n--- Task 2: Functional Interfaces with Lambdas ---");

        MathOperation add      = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide   = (a, b) -> b != 0 ? a / b : 0;

        int x = 20, y = 5;
        System.out.println(x + " + " + y + " = " + add.operate(x, y));
        System.out.println(x + " - " + y + " = " + subtract.operate(x, y));
        System.out.println(x + " * " + y + " = " + multiply.operate(x, y));
        System.out.println(x + " / " + y + " = " + divide.operate(x, y));

        Greeting formal   = name -> "Good morning, " + name + "!";
        Greeting informal = name -> "Hey, " + name + "!";
        System.out.println(formal.greet("Professor Khan"));
        System.out.println(informal.greet("Ali"));

        // Validator
        Validator<Integer> isPositive = n -> n > 0;
        Validator<String>  isEmail    = s -> s.contains("@") && s.contains(".");
        System.out.println("Is 10 positive? " + isPositive.validate(10));
        System.out.println("Is -5 positive? " + isPositive.validate(-5));
        System.out.println("Is 'user@ric.edu.pk' email? " + isEmail.validate("user@ric.edu.pk"));
        System.out.println("Is 'notanemail' email? " + isEmail.validate("notanemail"));

        // ---- Task 3: Built-in Functional Interfaces ----
        System.out.println("\n--- Task 3: Built-in Functional Interfaces ---");

        // Predicate<T> - returns boolean
        Predicate<Integer> isEven   = n -> n % 2 == 0;
        Predicate<Integer> isGtTen  = n -> n > 10;
        Predicate<Integer> evenAndGt10 = isEven.and(isGtTen);

        List<Integer> numbers = Arrays.asList(3, 8, 12, 15, 22, 7, 30, 5);
        System.out.print("Even numbers: ");
        numbers.stream().filter(isEven).forEach(n -> System.out.print(n + " "));

        System.out.print("\nEven AND > 10: ");
        numbers.stream().filter(evenAndGt10).forEach(n -> System.out.print(n + " "));

        // Function<T, R> - transforms T to R
        Function<String, Integer> strLen   = String::length;
        Function<Integer, Integer> doubled = n -> n * 2;
        Function<String, Integer> lenDoubled = strLen.andThen(doubled);

        System.out.println("\nLength of 'Advanced': " + strLen.apply("Advanced"));
        System.out.println("Length of 'Advanced' doubled: " + lenDoubled.apply("Advanced"));

        // Consumer<T> - performs action on T, returns void
        Consumer<String> printer    = s -> System.out.println(">> " + s);
        Consumer<String> upperPrint = s -> System.out.println(">> " + s.toUpperCase());
        Consumer<String> both = printer.andThen(upperPrint);

        System.out.println("Consumer.andThen:");
        both.accept("lambda demo");

        // Supplier<T> - returns T with no input
        Supplier<Double> randomScore = () -> Math.round(Math.random() * 400.0) / 100.0;
        System.out.println("Random GPA (Supplier): " + randomScore.get());

        // BiFunction<T, U, R>
        BiFunction<String, Double, String> studentInfo =
            (name, gpa) -> "Student: " + name + " | GPA: " + gpa;
        System.out.println(studentInfo.apply("Usman", 3.85));

        // UnaryOperator (special Function<T,T>)
        UnaryOperator<String> shoutify = s -> s.toUpperCase() + "!!!";
        System.out.println(shoutify.apply("java lambdas are cool"));

        // BinaryOperator (special BiFunction<T,T,T>)
        BinaryOperator<Integer> max = (a, b) -> a >= b ? a : b;
        System.out.println("Max of 42 and 17: " + max.apply(42, 17));

        System.out.println("\n--- Stream Pipeline with multiple lambdas ---");
        List<String> students = Arrays.asList("Usman", "Sara", "Ahmed", "Zara", "Muhammad", "Bilal");
        students.stream()
                .filter(s -> s.length() > 4)           // Predicate
                .map(String::toUpperCase)               // Method Reference
                .sorted()
                .forEach(s -> System.out.println("  " + s));
    }
}
