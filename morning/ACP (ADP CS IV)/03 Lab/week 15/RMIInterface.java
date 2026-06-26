import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Task 1 & 2: Distributed RMI Remote Calculator Service Interface.
 * 
 * Declares the remote contract. Extends java.rmi.Remote.
 */
public interface RMIInterface extends Remote {
    double add(double x, double y) throws RemoteException;
    double subtract(double x, double y) throws RemoteException;
    double multiply(double x, double y) throws RemoteException;
    double divide(double x, double y) throws RemoteException;
}
