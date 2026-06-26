/**
 * Task 3: Demonstrate thread lifecycle states.
 * 
 * Inspects and prints thread states: NEW, RUNNABLE, TIMED_WAITING, and TERMINATED.
 */
public class ThreadLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Thread Lifecycle State Transitions Demo ---");

        // Create a worker thread containing sleep delays
        Thread worker = new Thread(() -> {
            try {
                // Thread will enter TIMED_WAITING state during sleep
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Worker interrupted.");
            }
        });

        // State 1: NEW (created but start() has not been called)
        System.out.println("1. After instantiation (Before start()): State = " + worker.getState());

        // State 2: RUNNABLE (after start(), JVM executes the thread run code)
        worker.start();
        System.out.println("2. After calling start(): State = " + worker.getState());

        // Sleep main thread briefly to allow worker to enter sleep state
        Thread.sleep(100);

        // State 3: TIMED_WAITING (worker thread is currently inside Thread.sleep())
        System.out.println("3. While worker is sleeping (Thread.sleep()): State = " + worker.getState());

        // Wait for worker thread to finish executing
        worker.join();

        // State 4: TERMINATED (worker thread has completed run() execution)
        System.out.println("4. After run() finishes (worker.join() complete): State = " + worker.getState());
    }
}
