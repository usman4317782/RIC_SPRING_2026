class Device {
    String type = "Smartphone";
}

public class ReferenceTypeDemo {
    public static void main(String[] args) {
        // Defining a reference variable 'myDevice' using the reference type 'Device'
        Device myDevice; 
        
        // Initializing the reference variable
        myDevice = new Device();
        
        System.out.println("Device Type: " + myDevice.type);
    }
}
