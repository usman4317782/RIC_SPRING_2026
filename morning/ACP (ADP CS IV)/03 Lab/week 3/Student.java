/**
 * Task 1: Create a Student class with attributes (name, rollNo, age) and methods.
 */
public class Student {
    // Attributes
    private String name;
    private String rollNo;
    private int age;
    
    // Default Constructor
    public Student() {
        this.name = "Unknown";
        this.rollNo = "N/A";
        this.age = 0;
    }
    
    // Parameterized Constructor
    public Student(String name, String rollNo, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
    }
    
    // Getters and Setters
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
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age value!");
        }
    }
    
    // Display Student Details Method
    public void displayDetails() {
        System.out.println("Student Details -> Name: " + name + " | Roll No: " + rollNo + " | Age: " + age);
    }
}
