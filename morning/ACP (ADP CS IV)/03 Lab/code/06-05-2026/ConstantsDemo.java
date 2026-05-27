/**
 * Concept: To use constants to store permanent data.
 * 
 * Constants are declared using the 'final' keyword.
 * By convention, constants are named in ALL_UPPER_CASE with underscores.
 */
public class ConstantsDemo {
    public static void main(String[] args) {
        // Declare a constant for PI
        final double PI = 3.14159;

        // Declare a constant for a fixed tax rate
        final double TAX_RATE = 0.15;

        double radius = 5.0;
        double area = PI * radius * radius;

        System.out.println("Radius: " + radius);
        System.out.println("Area of circle: " + area);
        System.out.println("Tax rate: " + (TAX_RATE * 100) + "%");

        // Trying to change a constant will cause a compile error
        // PI = 3.14; // Un-commenting this would cause an error!
    }
}
