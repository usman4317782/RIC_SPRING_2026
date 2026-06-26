import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Task 3: Implement a simple contact book that saves/loads contacts to/from a file.
 */

// Model class representing a contact
class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String phone;
    private String email;
    
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone + " | Email: " + email;
    }
}

public class ContactBook {
    private static final String DATA_FILE = "contacts.dat";
    private List<Contact> contacts = new ArrayList<>();
    
    public ContactBook() {
        loadContacts(); // Load existing contacts on startup
    }
    
    public void addContact(String name, String phone, String email) {
        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
        saveContacts(); // Save immediately
    }
    
    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact book is empty.");
        } else {
            System.out.println("\n--- Contact List ---");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }
    
    // Save contacts using serialization
    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(contacts);
            System.out.println("Contact list saved to disk.");
        } catch (IOException e) {
            System.out.println("Failed to save contacts: " + e.getMessage());
        }
    }
    
    // Load contacts using deserialization
    @SuppressWarnings("unchecked")
    private void loadContacts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No saved contact files found. Starting fresh.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Loaded " + contacts.size() + " contact(s) from disk.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contact list: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ContactBook book = new ContactBook();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        System.out.println("=== Contact Book Application ===");
        
        do {
            System.out.println("\n1. Add Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Exit");
            System.out.print("Enter choice (1-3): ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Please enter a valid number!");
                scanner.next(); // clear token
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    book.addContact(name, phone, email);
                    break;
                case 2:
                    book.displayContacts();
                    break;
                case 3:
                    System.out.println("Exiting Contact Book. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
        
        scanner.close();
    }
}
