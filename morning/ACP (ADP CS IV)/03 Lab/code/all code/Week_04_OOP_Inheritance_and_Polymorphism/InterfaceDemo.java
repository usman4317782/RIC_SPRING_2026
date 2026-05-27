/**
 * Lab Task 2: Create an interface and implement it in a class.
 * 
 * To compile: javac InterfaceDemo.java
 * To run: java InterfaceDemo
 */

// Interface definition
interface Drivable {
    // Constant (public static final implicitly)
    int SPEED_LIMIT = 120; // km/h

    // Abstract methods (public abstract implicitly)
    void startEngine();
    void accelerate(int speed);
    void stopEngine();
}

// Another interface to show multiple interface capability
interface GPSNavigator {
    void navigateTo(String destination);
}

// Class implementing both interfaces
class SmartCar implements Drivable, GPSNavigator {
    private String model;
    private int currentSpeed = 0;
    private boolean engineRunning = false;

    public SmartCar(String model) {
        this.model = model;
    }

    // Implementing Drivable methods
    @Override
    public void startEngine() {
        engineRunning = true;
        System.out.println(model + " engine started.");
    }

    @Override
    public void accelerate(int speed) {
        if (!engineRunning) {
            System.out.println("Cannot accelerate. Start the engine first.");
            return;
        }
        int attemptedSpeed = currentSpeed + speed;
        if (attemptedSpeed > SPEED_LIMIT) {
            // Bug fix: show the attempted speed so the warning is informative
            System.out.printf("Warning: Attempted %d km/h exceeds Speed Limit (%d km/h)! Capped.%n",
                    attemptedSpeed, SPEED_LIMIT);
            currentSpeed = SPEED_LIMIT;
        } else {
            currentSpeed = attemptedSpeed;
        }
        System.out.println(model + " is now travelling at " + currentSpeed + " km/h.");
    }

    @Override
    public void stopEngine() {
        engineRunning = false;
        currentSpeed = 0;
        System.out.println(model + " engine stopped.");
    }

    // Implementing GPSNavigator methods
    @Override
    public void navigateTo(String destination) {
        System.out.println("GPS: Routing to " + destination + "...");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("----- Interface Demonstration -----");
        SmartCar tesla = new SmartCar("Tesla Model S");
        
        // Polymorphism: using interface reference
        Drivable vehicle = tesla;
        GPSNavigator navigator = tesla;
        
        vehicle.startEngine();
        navigator.navigateTo("Downtown Central");
        vehicle.accelerate(50);
        vehicle.accelerate(80); // Speed limit warning should trigger
        vehicle.stopEngine();
    }
}
