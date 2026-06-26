/**
 * Task 2: Implement Runnable interface to create threads.
 * 
 * Shows how to define a task implementing Runnable and running it through Thread instances.
 */

// Class implementing Runnable
class TaskRunnable implements Runnable {
    private String taskName;

    public TaskRunnable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Runnable task '" + taskName + "' started on thread: " + Thread.currentThread().getName());
        for (int i = 1; i <= 4; i++) {
            System.out.println("Task '" + taskName + "' - iteration " + i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Task '" + taskName + "' interrupted.");
            }
        }
        System.out.println("Runnable task '" + taskName + "' completed.");
    }
}

public class RunnableInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("--- Starting Threads using Runnable Interface ---");

        // 1. Traditional: Instantiating the Runnable class and passing to Thread
        TaskRunnable runnable1 = new TaskRunnable("File Processor");
        Thread thread1 = new Thread(runnable1, "Worker-1");

        TaskRunnable runnable2 = new TaskRunnable("Log Analyzer");
        Thread thread2 = new Thread(runnable2, "Worker-2");

        // Start threads
        thread1.start();
        thread2.start();

        // 2. Modern: Implementing Runnable inline using Lambda Expressions
        Runnable lambdaRunnable = () -> {
            System.out.println("Inline Lambda Runnable running on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {}
            System.out.println("Inline Lambda Runnable completed.");
        };
        Thread thread3 = new Thread(lambdaRunnable, "Worker-3");
        thread3.start();
    }
}
