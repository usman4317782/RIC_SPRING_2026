public class TypeCasting {
    public static void main(String[] args) {
        // Widening Casting (automatically) - converting a smaller type to a larger type
        // size
        int myInt = 9;
        double myDouble = myInt; // Automatic casting: int to double

        System.out.println("Widening Casting:");
        System.out.println("Int value: " + myInt); // Outputs 9
        System.out.println("Double value: " + myDouble); // Outputs 9.0

        // Narrowing Casting (manually) - converting a larger type to a smaller size
        // type
        double secondDouble = 9.78d;
        int secondInt = (int) secondDouble; // Manual casting: double to int

        System.out.println("\nNarrowing Casting:");
        System.out.println("Double value: " + secondDouble); // Outputs 9.78
        System.out.println("Int value: " + secondInt); // Outputs 9
    }
}
