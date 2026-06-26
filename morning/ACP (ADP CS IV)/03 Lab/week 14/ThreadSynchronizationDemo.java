/**
 * Task 1: Implement thread synchronization for a shared resource.
 * 
 * Shows race condition issues and how 'synchronized' blocks neutralize race conditions.
 */
class SharedCounter {
    private int count = 0;

    // Unsynchronized increment (susceptible to race conditions)
    public void incrementUnsafe() {
        int temp = count;
        try {
            // Sleep briefly to simulate context switching and expose race conditions
            Thread.sleep(1);
        } catch (InterruptedException e) {}
        count = temp + 1;
    }

    // Synchronized increment (thread-safe, mutual exclusion)
    public synchronized void incrementSafe() {
        int temp = count;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {}
        count = temp + 1;
    }

    public int getCount() {
        return count;
    }
    
    public void reset() {
        count = 0;
    }
}

public class ThreadSynchronizationDemo {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();

        // SCENARIO 1: Unsynchronized operations
        System.out.println("--- Scenario 1: Unsafe Increment (No Synchronization) ---");
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementUnsafe();
            }
        };

        Thread t1 = new Thread(unsafeTask);
        Thread t2 = new Thread(unsafeTask);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected Final Count: 200");
        System.out.println("Actual Unsafe Count  : " + counter.getCount());
        System.out.println("Status: " + (counter.getCount() == 200 ? "SUCCESS (Lucky run)" : "FAILED (Race condition occurred!)"));

        // SCENARIO 2: Synchronized operations
        System.out.println("\n--- Scenario 2: Safe Increment (With Synchronization) ---");
        counter.reset();
        
        Runnable safeTask = () -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementSafe();
            }
        };

        Thread t3 = new Thread(safeTask);
        Thread t4 = new Thread(safeTask);

        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Expected Final Count: 200");
        System.out.println("Actual Safe Count    : " + counter.getCount());
        System.out.println("Status: " + (counter.getCount() == 200 ? "SUCCESS (Thread Safe!)" : "FAILED"));
    }
}
