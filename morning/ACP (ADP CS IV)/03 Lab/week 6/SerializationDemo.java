import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Task 2: Create a program that serializes and deserializes objects.
 */

// A class must implement Serializable to be written/read via Object Streams
class UserProfile implements Serializable {
    // SerialVersionUID is used for JVM object version verification
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String email;
    // transient variables are ignored during serialization
    private transient String password; 
    
    public UserProfile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' + // Will be null after deserialization
                '}';
    }
}

public class SerializationDemo {
    private static final String SERIALIZED_FILE = "user_profile.ser";

    public static void main(String[] args) {
        UserProfile originalProfile = new UserProfile("admin_jane", "jane@example.com", "secret123");
        
        System.out.println("Original Profile Object:");
        System.out.println(originalProfile);
        System.out.println();
        
        // 1. Serialization: Writing Object to File
        System.out.println("--- Starting Serialization ---");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE))) {
            oos.writeObject(originalProfile);
            System.out.println("Object state serialized and saved to " + SERIALIZED_FILE);
        } catch (IOException e) {
            System.out.println("Serialization Error: " + e.getMessage());
        }
        
        System.out.println();
        
        // 2. Deserialization: Reading Object from File
        System.out.println("--- Starting Deserialization ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE))) {
            UserProfile loadedProfile = (UserProfile) ois.readObject();
            System.out.println("Object loaded and deserialized successfully.");
            System.out.println("Deserialized Profile Object:");
            System.out.println(loadedProfile);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization Error: " + e.getMessage());
        }
    }
}
