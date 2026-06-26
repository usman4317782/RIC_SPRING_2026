/**
 * Task 3: Demonstrate polymorphism through method overriding.
 */

// Base class
class Animal {
    public void makeSound() {
        System.out.println("The animal makes a generic sound.");
    }
}

// Subclass Dog overriding base class method
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The dog barks: Woof! Woof!");
    }
}

// Subclass Cat overriding base class method
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("The cat meows: Meow! Purr!");
    }
}

// Main Driver Class
public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("--- Polymorphism (Method Overriding) Demo ---");
        
        // Array of Animal reference variables
        Animal[] animals = new Animal[3];
        
        // Storing different subclass objects in parent class array slots
        animals[0] = new Animal();
        animals[1] = new Dog();
        animals[2] = new Cat();
        
        // Iterating and invoking overridden methods
        for (Animal animal : animals) {
            // JVM resolves method to call at runtime based on the actual object type
            animal.makeSound(); 
        }
    }
}
