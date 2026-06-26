import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Task 2: Implement the remote calculator service.
 * 
 * Extends UnicastRemoteObject to establish remote listening capabilities.
 */
public class RMIImpl extends UnicastRemoteObject implements RMIInterface {
    
    // Explicit constructor throwing RemoteException is required
    public RMIImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double x, double y) throws RemoteException {
        System.out.println("RMI Serving: add(" + x + ", " + y + ")");
        return x + y;
    }

    @Override
    public double subtract(double x, double y) throws RemoteException {
        System.out.println("RMI Serving: subtract(" + x + ", " + y + ")");
        return x - y;
    }

    @Override
    public double multiply(double x, double y) throws RemoteException {
        System.out.println("RMI Serving: multiply(" + x + ", " + y + ")");
        return x * y;
    }

    @Override
    public double divide(double x, double y) throws RemoteException {
        System.out.println("RMI Serving: divide(" + x + ", " + y + ")");
        if (y == 0) {
            throw new RemoteException("Division by zero error!");
        }
        return x / y;
    }
}
