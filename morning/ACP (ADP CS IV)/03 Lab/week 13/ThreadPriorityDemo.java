/**
 * Task 4: Create a program showing thread priorities.
 * 
 * Shows how setting thread priorities (MAX_PRIORITY, NORM_PRIORITY, MIN_PRIORITY) 
 * gives hints to the OS thread scheduler.
 */
class PriorityWorker extends Thread {
    private volatile boolean running = true;
    private long count = 0;

    public PriorityWorker(String name) {
        super(name);
    }

    public void stopWorker() {
        running = false;
    }

    public long getCount() {
        return count;
    }

    @Override
    public void run() {
        // Increment counter in a tight loop as fast as possible
        while (running) {
            count++;
        }
        System.out.println(getName() + " [Priority: " + getPriority() + "] finished. Count = " + count);
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Thread Priority Demonstration ---");
        System.out.println("Spawning 3 workers with different priorities...");

        PriorityWorker low = new PriorityWorker("Low-Priority-Worker");
        PriorityWorker medium = new PriorityWorker("Medium-Priority-Worker");
        PriorityWorker high = new PriorityWorker("High-Priority-Worker");

        // Set Priorities
        low.setPriority(Thread.MIN_PRIORITY);     // Priority 1
        medium.setPriority(Thread.NORM_PRIORITY); // Priority 5
        high.setPriority(Thread.MAX_PRIORITY);    // Priority 10

        // Start all threads simultaneously
        low.start();
        medium.start();
        high.start();

        // Let them run for 1 second to do work
        System.out.println("Workers are running... please wait.");
        Thread.sleep(1000);

        // Stop all workers
        low.stopWorker();
        medium.stopWorker();
        high.stopWorker();

        // Wait for threads to terminate
        low.join();
        medium.join();
        high.join();

        System.out.println("\nExecution Summary:");
        System.out.println("  Low Priority Count   : " + low.getCount());
        System.out.println("  Medium Priority Count: " + medium.getCount());
        System.out.println("  High Priority Count  : " + high.getCount());
        System.out.println("Note: High priority threads generally receive more CPU time slices.");
    }
}
