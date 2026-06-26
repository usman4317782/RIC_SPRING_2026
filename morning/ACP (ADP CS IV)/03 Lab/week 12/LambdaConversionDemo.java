import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Task 1: Convert anonymous inner classes to lambda expressions.
 * 
 * Shows comparison of anonymous inner class syntax vs. modern clean Lambda expressions.
 */
public class LambdaConversionDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. Converting Runnable Interface ===");

        // Traditional: Anonymous Inner Class syntax
        Runnable runOld = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable (Old Way) is running from: " + Thread.currentThread().getName());
            }
        };
        new Thread(runOld).start();

        // Modern: Lambda expression syntax
        // Lambdas omit the method name, parameter types (inferred), and class scaffolding
        Runnable runLambda = () -> System.out.println("Runnable (Lambda Way) is running from: " + Thread.currentThread().getName());
        new Thread(runLambda).start();

        // Sleep briefly to let the threads execute before outputting sorting logs
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        System.out.println("\n=== 2. Converting Comparator Interface ===");
        List<String> listOld = new ArrayList<>();
        listOld.add("Orange");
        listOld.add("Apple");
        listOld.add("Banana");
        List<String> listLambda = new ArrayList<>(listOld);

        // Traditional: Anonymous Inner Class sorting
        Collections.sort(listOld, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println("Sorted (Old Way):     " + listOld);

        // Modern: Lambda expression sorting
        // (s1, s2) -> s1.compareTo(s2)
        Collections.sort(listLambda, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted (Lambda Way):  " + listLambda);
    }
}
