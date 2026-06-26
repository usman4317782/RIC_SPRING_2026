/**
 * Task 4: Write a program showing method overloading.
 * 
 * Shows a utility class `MathUtils` with overloaded `add` and `format` methods.
 */
public class MethodOverloadingDemo {
    
    // 1. Add method for two integers
    public int add(int a, int b) {
        return a + b;
    }
    
    // 2. Add method for three integers (overloaded by number of parameters)
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // 3. Add method for two doubles (overloaded by data type)
    public double add(double a, double b) {
        return a + b;
    }
    
    // 4. Overloaded string formatting methods
    public String formatValue(int val) {
        return String.format("Integer Value: [%d]", val);
    }
    
    public String formatValue(double val) {
        return String.format("Double Value: [%.4f]", val);
    }
    
    public String formatValue(String val) {
        return String.format("String Value: \"%s\"", val);
    }

    public static void main(String[] args) {
        MethodOverloadingDemo demo = new MethodOverloadingDemo();
        
        System.out.println("--- Overloading by Number / Type of Parameters ---");
        System.out.println("add(10, 20)        = " + demo.add(10, 20));
        System.out.println("add(10, 20, 30)    = " + demo.add(10, 20, 30));
        System.out.println("add(10.5, 20.7)    = " + demo.add(10.5, 20.7));
        
        System.out.println("\n--- Overloading by Parameter Type for Formatting ---");
        System.out.println(demo.formatValue(42));
        System.out.println(demo.formatValue(3.14159));
        System.out.println(demo.formatValue("Hello Overloading"));
    }
}
