/**
 * Lab Tasks 1 & 3: Create a Student class with attributes (name, rollNo, age) and methods.
 * Demonstrate creation and usage of multiple objects of this class.
 * 
 * To compile: javac StudentDemo.java
 * To run: java StudentDemo
 */
class Student {
    // Attributes
    private String name;
    private String rollNo;
    private int age;

    // Constructor
    public Student(String name, String rollNo, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
    }

    // Methods
    public void displayDetails() {
        System.out.println("Roll No: " + rollNo + " | Name: " + name + " | Age: " + age);
    }

    // Getter and setter methods for demonstration
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        System.out.println("----- Student Object Creation Demo -----");
        
        // Creating multiple student objects
        Student s1 = new Student("Alice Smith", "CS-2026-001", 20);
        Student s2 = new Student("Bob Jones", "CS-2026-002", 21);
        Student s3 = new Student("Charlie Brown", "CS-2026-003", 19);
        
        // Display details
        System.out.println("Initially created students:");
        s1.displayDetails();
        s2.displayDetails();
        s3.displayDetails();
        
        // Modify details of s3
        System.out.println("\nModifying Charlie's details using Setters...");
        s3.setName("Charlie W. Brown");
        s3.setAge(20);
        
        System.out.println("Modified Details of s3:");
        s3.displayDetails();
    }
}
