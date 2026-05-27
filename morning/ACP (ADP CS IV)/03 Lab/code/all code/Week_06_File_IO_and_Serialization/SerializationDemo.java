import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Lab Task 2: Create a program that serializes and deserializes objects.
 * 
 * To compile: javac SerializationDemo.java
 * To run: java SerializationDemo
 */
class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for serializable classes
    
    private String name;
    private int id;
    private transient double salary; // 'transient' fields are NOT serialized!

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{ID=" + id + ", Name='" + name + "', Salary=$" + salary + " (transient)}";
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        String filename = "employee.ser";
        Employee emp = new Employee("John Watson", 104, 7500.0);
        
        System.out.println("----- Phase 1: Serializing Object -----");
        System.out.println("Original Object: " + emp);
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(emp);
            System.out.println("Employee object serialized and saved to '" + filename + "'");
        } catch (Exception e) {
            System.err.println("Serialization Error: " + e.getMessage());
        }

        System.out.println("\n----- Phase 2: Deserializing Object -----");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Employee deserializedEmp = (Employee) in.readObject();
            System.out.println("Employee object deserialized successfully.");
            System.out.println("Deserialized Object: " + deserializedEmp);
            System.out.println("Note: salary is $0.0 because it was marked transient!");
        } catch (Exception e) {
            System.err.println("Deserialization Error: " + e.getMessage());
        }
    }
}
