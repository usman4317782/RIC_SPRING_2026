import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI Server class that initializes the local RMI Registry and binds the 
 * Remote Calculator implementation instance.
 */
public class RMIServer {
    public static void main(String[] args) {
        System.out.println("=== Starting RMI Server ===");
        try {
            // 1. Start RMI Registry locally on standard port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry created on port 1099.");

            // 2. Instantiate the Remote Service implementation
            RMIInterface calculatorService = new RMIImpl();

            // 3. Bind/register the implementation under a unique name
            registry.rebind("CalculatorService", calculatorService);
            System.out.println("CalculatorService bound in registry. Ready to receive client calls.");

        } catch (Exception e) {
            System.err.println("RMI Server failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
