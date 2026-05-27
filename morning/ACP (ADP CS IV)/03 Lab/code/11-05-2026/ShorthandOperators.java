public class ShorthandOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println("Initial a: " + a);

        // Addition assignment
        a += 5; // Equivalent to a = a + 5
        System.out.println("After a += 5: " + a);

        // Subtraction assignment
        a -= 3; // Equivalent to a = a - 3
        System.out.println("After a -= 3: " + a);

        // Multiplication assignment
        a *= 2; // Equivalent to a = a * 2
        System.out.println("After a *= 2: " + a);

        // Division assignment
        a /= 4; // Equivalent to a = a / 4
        System.out.println("After a /= 4: " + a);

        // Remainder assignment
        a %= 2; // Equivalent to a = a % 2
        System.out.println("After a %= 2: " + a);
    }
}
