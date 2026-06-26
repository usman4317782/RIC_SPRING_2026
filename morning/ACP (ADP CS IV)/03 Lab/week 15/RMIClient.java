import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI Client class that retrieves the remote CalculatorService stub from the 
 * RMI Registry and executes calculations.
 */
public class RMIClient {
    public static void main(String[] args) {
        System.out.println("=== Starting RMI Client ===");
        try {
            // 1. Fetch registry lookup handle for localhost port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // 2. Lookup the service by registered name
            RMIInterface calculator = (RMIInterface) registry.lookup("CalculatorService");
            System.out.println("Remote CalculatorService stub obtained successfully.");

            // 3. Invoke remote operations
            double val1 = 15.0;
            double val2 = 3.0;
            
            System.out.println("\nExecuting Remote Arithmetic Operations:");
            System.out.println("  " + val1 + " + " + val2 + " = " + calculator.add(val1, val2));
            System.out.println("  " + val1 + " - " + val2 + " = " + calculator.subtract(val1, val2));
            System.out.println("  " + val1 + " * " + val2 + " = " + calculator.multiply(val1, val2));
            System.out.println("  " + val1 + " / " + val2 + " = " + calculator.divide(val1, val2));
            
            // Testing exception behavior (division by zero)
            System.out.println("\nTesting Remote Error Catching (dividing by zero):");
            try {
                calculator.divide(val1, 0);
            } catch (Exception ex) {
                System.out.println("  Caught expected exception: " + ex.getMessage());
            }

        } catch (Exception e) {
            System.err.println("RMI Client exception occurred: " + e.getMessage());
            System.err.println("Make sure RMIServer is running before executing client.");
            e.printStackTrace();
        }
    }
}
