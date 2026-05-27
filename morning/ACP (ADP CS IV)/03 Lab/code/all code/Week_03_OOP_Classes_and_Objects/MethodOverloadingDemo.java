/**
 * Lab Task 4: Write a program showing method overloading.
 * Overloading occurs when multiple methods have the same name but different parameter lists.
 * 
 * To compile: javac MethodOverloadingDemo.java
 * To run: java MethodOverloadingDemo
 */
class AreaCalculator {
    
    // 1. Overloaded method to calculate area of a square (1 integer parameter)
    public double calculateArea(int side) {
        System.out.println("Calculating area of Square (int side):");
        return side * side;
    }
    
    // 2. Overloaded method to calculate area of a rectangle (2 integer parameters)
    public double calculateArea(int length, int width) {
        System.out.println("Calculating area of Rectangle (int length, int width):");
        return length * width;
    }
    
    // 3. Overloaded method to calculate area of a circle (1 double parameter)
    public double calculateArea(double radius) {
        System.out.println("Calculating area of Circle (double radius):");
        return Math.PI * radius * radius;
    }
    
    // 4. Overloaded method to calculate area of a triangle (2 double parameters)
    public double calculateArea(double base, double height) {
        System.out.println("Calculating area of Triangle (double base, double height):");
        return 0.5 * base * height;
    }
}

public class MethodOverloadingDemo {
    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();
        
        System.out.println("----- Method Overloading Demonstration -----");
        
        double squareArea = calc.calculateArea(5);
        System.out.println("Square Area: " + squareArea + "\n");
        
        double rectArea = calc.calculateArea(6, 8);
        System.out.println("Rectangle Area: " + rectArea + "\n");
        
        double circleArea = calc.calculateArea(3.5);
        System.out.printf("Circle Area: %.4f\n\n", circleArea);
        
        double triangleArea = calc.calculateArea(4.0, 7.5);
        System.out.println("Triangle Area: " + triangleArea + "\n");
    }
}
