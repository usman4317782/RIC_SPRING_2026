/**
 * Task 2: Create an interface and implement it in a class.
 */

// Interface definition
interface Drivable {
    void startEngine();
    void accelerate(int speed);
    void stop();
}

// Class implementing the interface
class Car implements Drivable {
    private String model;
    private int currentSpeed = 0;
    
    public Car(String model) {
        this.model = model;
    }
    
    @Override
    public void startEngine() {
        System.out.println(model + ": Engine started. Ready to drive.");
    }
    
    @Override
    public void accelerate(int speed) {
        currentSpeed += speed;
        System.out.println(model + ": Accelerated by " + speed + " km/h. Current speed: " + currentSpeed + " km/h.");
    }
    
    @Override
    public void stop() {
        currentSpeed = 0;
        System.out.println(model + ": Engine stopped. Car came to a halt.");
    }
}

// Main Driver Class
public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("--- Interface Implementation Demo ---");
        
        Drivable myCar = new Car("Toyota Supra");
        myCar.startEngine();
        myCar.accelerate(60);
        myCar.accelerate(40);
        myCar.stop();
    }
}
