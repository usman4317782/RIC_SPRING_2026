/**
 * Lab Tasks 1 & 3: Implement inheritance with a Person -> Student -> GraduateStudent hierarchy.
 * Demonstrate polymorphism through method overriding.
 * 
 * To compile: javac InheritanceDemo.java
 * To run: java InheritanceDemo
 */

// Superclass
class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Overridable method
    public void displayInfo() {
        System.out.println("Person Name: " + name + ", Age: " + age);
    }
}

// Subclass of Person
class Student extends Person {
    protected String rollNo;

    public Student(String name, int age, String rollNo) {
        super(name, age);
        this.rollNo = rollNo;
    }

    // Overriding displayInfo
    @Override
    public void displayInfo() {
        System.out.println("Student Name: " + name + ", Age: " + age + ", Roll No: " + rollNo);
    }
    
    public void study() {
        System.out.println(name + " is studying hard.");
    }
}

// Subclass of Student (Grandchild of Person)
class GraduateStudent extends Student {
    protected String researchTopic;

    public GraduateStudent(String name, int age, String rollNo, String researchTopic) {
        super(name, age, rollNo);
        this.researchTopic = researchTopic;
    }

    // Overriding displayInfo
    @Override
    public void displayInfo() {
        System.out.println("Graduate Student Name: " + name + ", Age: " + age + 
                           ", Roll No: " + rollNo + ", Research Topic: " + researchTopic);
    }
    
    public void conductResearch() {
        System.out.println(name + " is conducting research on: " + researchTopic);
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("----- Inheritance and Overriding Demo -----");
        
        Person p = new Person("Generic Person", 40);
        Student s = new Student("Bob Student", 21, "S123");
        GraduateStudent g = new GraduateStudent("Alice Grad", 25, "G456", "Quantum Computing");
        
        System.out.println("\nCalling displayInfo() directly on each reference type:");
        p.displayInfo();
        s.displayInfo();
        g.displayInfo();
        
        System.out.println("\nCalling displayInfo() polymorphically using Person references:");
        Person[] group = new Person[3];
        group[0] = p;
        group[1] = s;  // Upcasting
        group[2] = g;  // Upcasting
        
        for (Person member : group) {
            member.displayInfo(); // Dynamic binding executes the overridden subclass methods
        }
    }
}
