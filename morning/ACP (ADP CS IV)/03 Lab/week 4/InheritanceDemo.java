/**
 * Task 1: Implement inheritance with a Person -> Student -> GraduateStudent hierarchy.
 */

// Base Class
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void displayInfo() {
        System.out.println("Person: " + name + ", Age: " + age);
    }
}

// Student Class inherits Person
class Student extends Person {
    private String studentId;
    
    public Student(String name, int age, String studentId) {
        super(name, age); // Call base class constructor
        this.studentId = studentId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Student: " + getName() + ", Age: " + getAge() + ", Student ID: " + studentId);
    }
}

// GraduateStudent inherits Student
class GraduateStudent extends Student {
    private String researchTopic;
    
    public GraduateStudent(String name, int age, String studentId, String researchTopic) {
        super(name, age, studentId); // Call parent class constructor
        this.researchTopic = researchTopic;
    }
    
    public String getResearchTopic() {
        return researchTopic;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Graduate Student: " + getName() + ", Age: " + getAge() + 
                           ", Student ID: " + getStudentId() + ", Research: " + researchTopic);
    }
}

// Main Driver Class
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("--- Inheritance Hierarchy Demo ---");
        
        Person person = new Person("John Doe", 45);
        Student student = new Student("Alice Cooper", 20, "S9001");
        GraduateStudent gradStudent = new GraduateStudent("Dr. Bob Ross", 26, "G5002", "Neural Network Security");
        
        person.displayInfo();
        student.displayInfo();
        gradStudent.displayInfo();
    }
}
