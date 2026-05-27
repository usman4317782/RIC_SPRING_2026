/**
 * Lab Task 1: Create and run simple threads by extending the Thread class.
 * Lab Task 3: Demonstrate thread lifecycle states.
 * Lab Task 4: Create a program showing thread priorities.
 *
 * Demonstrates:
 *  - Creating threads by extending Thread
 *  - Thread states: NEW, RUNNABLE, TIMED_WAITING, TERMINATED
 *  - Thread priorities (MIN, NORM, MAX)
 *  - Joining threads
 *
 * To compile: javac ThreadExtendDemo.java
 * To run:     java ThreadExtendDemo
 */
public class ThreadExtendDemo {

    // ---- Thread by Extending ----
    static class CounterThread extends Thread {
        private final String threadName;
        private final int    count;
        private final int    delayMs;

        public CounterThread(String name, int count, int delayMs) {
            super(name);    // sets the thread name
            this.threadName = name;
            this.count      = count;
            this.delayMs    = delayMs;
        }

        @Override
        public void run() {
            System.out.println("[" + threadName + "] Started. State: " + getState());
            for (int i = 1; i <= count; i++) {
                System.out.printf("[%s] Count = %d%n", threadName, i);
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    System.out.println("[" + threadName + "] Interrupted!");
                    return;
                }
            }
            System.out.println("[" + threadName + "] Finished.");
        }
    }

    // ---- Priority Thread ----
    static class PriorityWorker extends Thread {
        private int tasksDone = 0;

        public PriorityWorker(String name, int priority) {
            super(name);
            setPriority(priority);
        }

        @Override
        public void run() {
            long end = System.currentTimeMillis() + 500; // run for 500ms
            while (System.currentTimeMillis() < end) {
                tasksDone++;
            }
        }

        public int getTasksDone() { return tasksDone; }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("  Thread Demo (Extending Thread) - ACP Week 13   ");
        System.out.println("=================================================\n");

        // ---- Task 1: Creating and starting threads ----
        System.out.println("--- Task 1: Creating & Running Threads ---");
        CounterThread t1 = new CounterThread("Thread-Alpha", 4, 200);
        CounterThread t2 = new CounterThread("Thread-Beta",  4, 300);

        System.out.println("State before start: " + t1.getState()); // NEW
        t1.start();
        t2.start();
        System.out.println("State after start:  " + t1.getState()); // RUNNABLE or TIMED_WAITING

        // ---- Task 3: Thread Lifecycle States ----
        System.out.println("\n--- Task 3: Thread Lifecycle ---");
        Thread lifecycleThread = new Thread(() -> {
            try { Thread.sleep(400); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Lifecycle-Thread");

        System.out.println("Before start:  " + lifecycleThread.getState()); // NEW
        lifecycleThread.start();
        Thread.sleep(50);
        System.out.println("While running: " + lifecycleThread.getState()); // TIMED_WAITING
        lifecycleThread.join();
        System.out.println("After finish:  " + lifecycleThread.getState()); // TERMINATED

        t1.join();
        t2.join();

        // ---- Task 4: Thread Priorities ----
        System.out.println("\n--- Task 4: Thread Priorities ---");
        System.out.println("MIN_PRIORITY=" + Thread.MIN_PRIORITY +
                           ", NORM_PRIORITY=" + Thread.NORM_PRIORITY +
                           ", MAX_PRIORITY=" + Thread.MAX_PRIORITY);

        PriorityWorker lowPriority  = new PriorityWorker("LowPriority",  Thread.MIN_PRIORITY);
        PriorityWorker normPriority = new PriorityWorker("NormPriority", Thread.NORM_PRIORITY);
        PriorityWorker highPriority = new PriorityWorker("HighPriority", Thread.MAX_PRIORITY);

        lowPriority.start();
        normPriority.start();
        highPriority.start();

        lowPriority.join();
        normPriority.join();
        highPriority.join();

        System.out.println("Tasks completed in 500ms:");
        System.out.printf("  %-15s (priority %d): %,d tasks%n", lowPriority.getName(),  lowPriority.getPriority(),  lowPriority.getTasksDone());
        System.out.printf("  %-15s (priority %d): %,d tasks%n", normPriority.getName(), normPriority.getPriority(), normPriority.getTasksDone());
        System.out.printf("  %-15s (priority %d): %,d tasks%n", highPriority.getName(), highPriority.getPriority(), highPriority.getTasksDone());
        System.out.println("\n(Note: Priority effects depend on the OS scheduler and JVM implementation)");

        System.out.println("\nAll threads completed.");
    }
}
