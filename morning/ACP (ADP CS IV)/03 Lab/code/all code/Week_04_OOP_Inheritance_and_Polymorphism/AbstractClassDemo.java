/**
 * Lab Task 4: Create an abstract class with concrete and abstract methods.
 * 
 * To compile: javac AbstractClassDemo.java
 * To run: java AbstractClassDemo
 */

// Abstract class definition
abstract class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;

    public Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    // Concrete method - shared logic for all employees
    public void displayBasicInfo() {
        System.out.println("Employee ID: " + id + " | Name: " + name + " | Base Salary: $" + baseSalary);
    }

    // Abstract method - subclasses must implement specific salary calculation logic
    public abstract double calculateSalary();
}

// Concrete subclass 1
class FullTimeEmployee extends Employee {
    private double bonus;

    public FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    // Implementing the abstract method
    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}

// Concrete subclass 2
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id, 0); // Part-time might have 0 base salary and earn purely hourly
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    // Implementing the abstract method
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

public class AbstractClassDemo {
    public static void main(String[] args) {
        System.out.println("----- Abstract Class Demonstration -----");
        
        // Employee e = new Employee("Name", 1, 1000); // Error: Employee is abstract; cannot be instantiated
        
        Employee emp1 = new FullTimeEmployee("David", 101, 5000.0, 1200.0);
        Employee emp2 = new PartTimeEmployee("Sarah", 202, 80, 25.0);
        
        System.out.println("\n--- Full-Time Employee ---");
        emp1.displayBasicInfo();
        System.out.println("Calculated Monthly Take-Home: $" + emp1.calculateSalary());
        
        System.out.println("\n--- Part-Time Employee ---");
        emp2.displayBasicInfo();
        System.out.println("Calculated Monthly Take-Home: $" + emp2.calculateSalary());
    }
}
