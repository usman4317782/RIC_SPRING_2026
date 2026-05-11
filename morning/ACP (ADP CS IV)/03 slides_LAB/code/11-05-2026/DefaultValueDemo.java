class UserProfile {
    // Data fields of reference types (String)
    String username; 
    String email;
    
    // Numeric data field
    int age;
    
    // Boolean data field
    boolean isActive;
}

public class DefaultValueDemo {
    public static void main(String[] args) {
        UserProfile user = new UserProfile();

        // Demonstrating default values assigned by Java for an object's data fields
        System.out.println("Default values for UserProfile object:");
        System.out.println("Username (Reference type - String): " + user.username); // null
        System.out.println("Email (Reference type - String): " + user.email);       // null
        System.out.println("Age (int): " + user.age);                               // 0
        System.out.println("Is Active (boolean): " + user.isActive);               // false
    }
}
