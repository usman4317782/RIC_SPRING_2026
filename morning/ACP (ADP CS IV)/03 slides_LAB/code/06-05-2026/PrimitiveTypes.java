public class PrimitiveTypes {
    public static void main(String[] args) {
        // 1. byte (8-bit)
        byte smallNumber = 127;

        // 2. short (16-bit)
        short middleNumber = 32000;

        // 3. int (32-bit) - Most common for whole numbers
        int age = 2024;

        // 4. long (64-bit) - Used for very large numbers
        long population = 8000000000L; // Note the 'L' suffix

        // 5. float (32-bit) - Single precision decimal
        float price = 19.99f; // Note the 'f' suffix

        // 6. double (64-bit) - Double precision decimal (standard for math)
        double distance = 149600000.5;

        // 7. char (16-bit) - Stores a single character
        char grade = 'A';

        // Displaying the values
        System.out.println("byte: " + smallNumber);
        System.out.println("short: " + middleNumber);
        System.out.println("int: " + age);
        System.out.println("long: " + population);
        System.out.println("float: " + price);
        System.out.println("double: " + distance);
        System.out.println("char: " + grade);
    }
}
