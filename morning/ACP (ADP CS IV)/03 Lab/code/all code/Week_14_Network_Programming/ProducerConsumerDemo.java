import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Lab Task 2: Create a producer-consumer problem solution.
 *
 * Demonstrates two approaches:
 *  A) Classic Producer-Consumer using wait()/notify() on a shared buffer
 *  B) Modern Producer-Consumer using BlockingQueue (simpler & preferred)
 *
 * To compile: javac ProducerConsumerDemo.java
 * To run:     java ProducerConsumerDemo
 */
public class ProducerConsumerDemo {

    // ======================================================
    // Approach A: wait() / notify() on shared buffer
    // ======================================================
    static class SharedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) { this.capacity = capacity; }

        public synchronized void produce(int item) throws InterruptedException {
            while (buffer.size() == capacity) {
                System.out.println("[Producer] Buffer FULL (" + capacity + "). Waiting...");
                wait();
            }
            buffer.offer(item);
            System.out.println("[Producer] Produced: " + item + " | Buffer: " + buffer);
            notifyAll();
        }

        public synchronized int consume() throws InterruptedException {
            while (buffer.isEmpty()) {
                System.out.println("[Consumer] Buffer EMPTY. Waiting...");
                wait();
            }
            int item = buffer.poll();
            System.out.println("[Consumer] Consumed: " + item + " | Buffer: " + buffer);
            notifyAll();
            return item;
        }
    }

    static class Producer extends Thread {
        private final SharedBuffer buf;
        private final int items;

        public Producer(SharedBuffer buf, int items) {
            super("Producer");
            this.buf   = buf;
            this.items = items;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= items; i++) {
                    buf.produce(i);
                    Thread.sleep(100);
                }
                System.out.println("[Producer] Done producing.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer extends Thread {
        private final SharedBuffer buf;
        private final int items;

        public Consumer(SharedBuffer buf, int items) {
            super("Consumer");
            this.buf   = buf;
            this.items = items;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < items; i++) {
                    buf.consume();
                    Thread.sleep(200);
                }
                System.out.println("[Consumer] Done consuming.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ======================================================
    // Approach B: BlockingQueue (modern, simpler approach)
    // ======================================================
    static class ModernProducer implements Runnable {
        private final BlockingQueue<String> queue;
        private final String[] items = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};

        public ModernProducer(BlockingQueue<String> queue) { this.queue = queue; }

        @Override
        public void run() {
            try {
                for (String item : items) {
                    queue.put(item);      // blocks if queue is full
                    System.out.println("[ModernProducer] Put: " + item + " | Queue size: " + queue.size());
                    Thread.sleep(150);
                }
                queue.put("DONE");        // poison pill to signal consumer to stop
                System.out.println("[ModernProducer] Sent DONE signal.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class ModernConsumer implements Runnable {
        private final BlockingQueue<String> queue;

        public ModernConsumer(BlockingQueue<String> queue) { this.queue = queue; }

        @Override
        public void run() {
            try {
                while (true) {
                    String item = queue.take();  // blocks if queue is empty
                    if ("DONE".equals(item)) {
                        System.out.println("[ModernConsumer] Received DONE signal. Stopping.");
                        break;
                    }
                    System.out.println("[ModernConsumer] Took: " + item);
                    Thread.sleep(250);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("  Producer-Consumer Demo - ACP Week 14            ");
        System.out.println("=================================================\n");

        // ---- Approach A: wait/notify ----
        System.out.println("========= Approach A: wait() / notify() =========");
        SharedBuffer buffer  = new SharedBuffer(3); // capacity 3
        Producer producer    = new Producer(buffer, 6);
        Consumer consumer    = new Consumer(buffer, 6);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // ---- Approach B: BlockingQueue ----
        System.out.println("\n======== Approach B: BlockingQueue (Modern) ======");
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(2); // capacity 2
        Thread modProd = new Thread(new ModernProducer(blockingQueue), "ModernProducer");
        Thread modCons = new Thread(new ModernConsumer(blockingQueue), "ModernConsumer");
        modProd.start();
        modCons.start();
        modProd.join();
        modCons.join();

        System.out.println("\nAll producer-consumer tasks completed successfully.");
    }
}
