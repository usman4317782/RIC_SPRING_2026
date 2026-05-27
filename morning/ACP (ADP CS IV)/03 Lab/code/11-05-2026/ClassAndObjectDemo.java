// #include <iostream>
// Defining a class
class Car {
    String model;
    int year;

    void displayInfo() {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}

public class ClassAndObjectDemo {
    public static void main(String[] args) {
        // Creating an object of the Car class
        Car myCar = new Car();
        // ClassName objName = newKeyWord ClassName();

        // Assigning values to object fields
        myCar.model = "Tesla Model 3";
        myCar.year = 2023;

        // Calling object methods
        myCar.displayInfo();
    }
}
