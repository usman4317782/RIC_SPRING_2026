class Calculator {
    int result;

    void add(int a, int b) {
        result = a + b;
    }
}

public class MemberAccessDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Accessing methods using the object member access operator (.)
        calc.add(15, 25);

        // Accessing data fields using the object member access operator (.)
        System.out.println("Calculation Result: " + calc.result);
    }
}
