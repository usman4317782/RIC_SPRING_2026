class Student {
    String name;
    int age;

    // No-arg constructor
    Student() {
        name = "Unknown";
        age = 0;
    }

    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void showDetails() {
        System.out.println("Student Name: " + name + ", Age: " + age);
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        // Creating objects using constructors
        Student s1 = new Student(); // Calls no-arg constructor
        Student s2 = new Student("Alice", 20); // Calls parameterized constructor

        s1.showDetails();
        s2.showDetails();
    }
}
