import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lab Task 3: Implement a simple contact book that saves/loads contacts to/from a file.
 * 
 * To compile: javac ContactBookDemo.java
 * To run: java ContactBookDemo
 */
class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void displayContact() {
        System.out.printf("Name: %-15s | Phone: %-15s | Email: %s\n", name, phoneNumber, email);
    }
}

class ContactBook {
    private List<Contact> contacts;
    private String filename;

    public ContactBook(String filename) {
        this.filename = filename;
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact Book is empty.");
        } else {
            System.out.println("--- Contacts List (" + contacts.size() + ") ---");
            for (Contact c : contacts) {
                c.displayContact();
            }
        }
    }

    // Save the entire list of contacts to a file
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(contacts);
            System.out.println("Contacts successfully saved to '" + filename + "'.");
        } catch (Exception e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Load the entire list of contacts from a file
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (List<Contact>) in.readObject();
            System.out.println("Contacts successfully loaded from '" + filename + "'.");
        } catch (Exception e) {
            System.out.println("No existing contact file found or error loading file. Starting fresh.");
            contacts = new ArrayList<>();
        }
    }
}

public class ContactBookDemo {
    public static void main(String[] args) {
        String filepath = "contacts.dat";
        
        System.out.println("----- Contact Book Instance 1: Adding and Saving -----");
        ContactBook book1 = new ContactBook(filepath);
        book1.addContact(new Contact("Alice", "+1-555-0101", "alice@example.com"));
        book1.addContact(new Contact("Bob", "+1-555-0202", "bob@example.com"));
        book1.addContact(new Contact("Charlie", "+1-555-0303", "charlie@example.com"));
        
        book1.listContacts();
        book1.saveToFile();

        System.out.println("\n----- Contact Book Instance 2: Loading and Displaying -----");
        ContactBook book2 = new ContactBook(filepath);
        // Load contacts from file (should contain Alice, Bob, Charlie)
        book2.loadFromFile();
        book2.listContacts();
    }
}
