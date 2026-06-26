import java.util.LinkedList;
import java.util.Queue;

/**
 * Task 2: Create a producer-consumer problem solution.
 * 
 * Demonstrates thread coordination using wait() and notify() over a shared buffer queue.
 */
class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Producer inserts an item into the buffer
    public synchronized void produce(int item) throws InterruptedException {
        // Wait if buffer is full
        while (queue.size() == capacity) {
            System.out.println("Buffer is FULL. Producer thread waiting...");
            wait(); // Releases monitor lock, yields execution
        }

        queue.add(item);
        System.out.println("Produced: " + item + " | Buffer size: " + queue.size());
        
        // Notify the consumer thread that an item is available
        notify(); 
    }

    // Consumer removes an item from the buffer
    public synchronized int consume() throws InterruptedException {
        // Wait if buffer is empty
        while (queue.isEmpty()) {
            System.out.println("Buffer is EMPTY. Consumer thread waiting...");
            wait();
        }

        int item = queue.poll();
        System.out.println("Consumed: " + item + " | Buffer size: " + queue.size());

        // Notify the producer thread that slot space is available
        notify(); 
        
        return item;
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(3); // Buffer capacity of 3

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(150); // Simulate time to produce
                }
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted.");
            }
        }, "Producer-Thread");

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(400); // Simulate time to consume (slower than producer)
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted.");
            }
        }, "Consumer-Thread");

        System.out.println("--- Starting Producer-Consumer Lifecycle ---");
        producer.start();
        consumer.start();
    }
}
