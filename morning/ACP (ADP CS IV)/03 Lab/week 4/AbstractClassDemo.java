/**
 * Task 4: Create an abstract class with concrete and abstract methods.
 */

// Abstract class
abstract class Shape {
    private String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Concrete method (has implementation)
    public String getColor() {
        return color;
    }
    
    public void displayColor() {
        System.out.println("This shape color is: " + color);
    }
    
    // Abstract method (no implementation, must be overridden by subclasses)
    public abstract double calculateArea();
}

// Subclass Circle
class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Subclass Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Main Driver Class
public class AbstractClassDemo {
    public static void main(String[] args) {
        System.out.println("--- Abstract Class Demo ---");
        
        Shape circle = new Circle("Red", 5.0);
        Shape rectangle = new Rectangle("Blue", 4.0, 6.0);
        
        circle.displayColor();
        System.out.println("Circle Area: " + String.format("%.2f", circle.calculateArea()));
        
        System.out.println();
        
        rectangle.displayColor();
        System.out.println("Rectangle Area: " + String.format("%.2f", rectangle.calculateArea()));
    }
}
