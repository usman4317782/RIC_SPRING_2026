/**
 * Task 1: Create and run simple threads by extending Thread class.
 */

// Subclass extending Thread
class PrintingThread extends Thread {
    public PrintingThread(String name) {
        super(name); // Set thread name
    }

    @Override
    public void run() {
        System.out.println(getName() + " started running.");
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " printing step: " + i);
            try {
                // Sleep thread for 300ms to allow scheduler to interleave other threads
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
        System.out.println(getName() + " finished execution.");
    }
}

public class ThreadExtensionDemo {
    public static void main(String[] args) {
        System.out.println("--- Starting Threads by Extending Thread Class ---");
        
        // Create thread objects
        PrintingThread thread1 = new PrintingThread("Thread A");
        PrintingThread thread2 = new PrintingThread("Thread B");
        
        // Start threads (calls run() asynchronously)
        thread1.start();
        thread2.start();
        
        System.out.println("Main thread finished initiating spawn operations.");
    }
}
