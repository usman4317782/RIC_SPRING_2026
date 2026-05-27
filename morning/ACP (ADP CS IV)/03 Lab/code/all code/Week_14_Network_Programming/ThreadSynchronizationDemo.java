import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lab Task 1: Implement thread synchronization for a shared resource.
 *
 * Demonstrates:
 *  1. Race condition WITHOUT synchronization
 *  2. Fixing with synchronized keyword
 *  3. Fixing with ReentrantLock (explicit lock)
 *  4. AtomicInteger (lock-free thread-safe operations)
 *
 * To compile: javac ThreadSynchronizationDemo.java
 * To run:     java ThreadSynchronizationDemo
 */
public class ThreadSynchronizationDemo {

    // ---- Unsafe Counter (demonstrates race condition) ----
    static class UnsafeCounter {
        private int count = 0;
        public void increment() { count++; }
        public int getCount()   { return count; }
    }

    // ---- Synchronized Counter ----
    static class SynchronizedCounter {
        private int count = 0;
        public synchronized void increment() { count++; }
        public synchronized int getCount()   { return count; }
    }

    // ---- ReentrantLock Counter ----
    static class LockCounter {
        private int  count = 0;
        private final Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try { count++; }
            finally { lock.unlock(); }
        }
        public int getCount() { return count; }
    }

    // ---- Shared Bank Account (synchronized) ----
    static class BankAccount {
        private double balance;
        private final String owner;

        public BankAccount(String owner, double balance) {
            this.owner   = owner;
            this.balance = balance;
        }

        public synchronized void deposit(double amount) {
            System.out.printf("[%s] Deposit $%.2f | Before: $%.2f%n",
                Thread.currentThread().getName(), amount, balance);
            balance += amount;
            System.out.printf("[%s]        | After : $%.2f%n",
                Thread.currentThread().getName(), balance);
        }

        public synchronized void withdraw(double amount) {
            if (balance < amount) {
                System.out.printf("[%s] Insufficient funds for $%.2f withdrawal (balance=$%.2f)%n",
                    Thread.currentThread().getName(), amount, balance);
                return;
            }
            System.out.printf("[%s] Withdraw $%.2f | Before: $%.2f%n",
                Thread.currentThread().getName(), amount, balance);
            balance -= amount;
            System.out.printf("[%s]         | After : $%.2f%n",
                Thread.currentThread().getName(), balance);
        }

        public synchronized double getBalance() { return balance; }
        public String getOwner() { return owner; }
    }

    // ---- Helper: run N threads all calling increment() M times ----
    static int runThreads(Runnable incrementOp, int numThreads, int perThread) throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int idx = i;
            threads[idx] = new Thread(() -> {
                for (int j = 0; j < perThread; j++) incrementOp.run();
            });
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("  Thread Synchronization Demo - ACP Week 14       ");
        System.out.println("=================================================\n");

        final int NUM_THREADS  = 10;
        final int PER_THREAD   = 1000;
        final int EXPECTED     = NUM_THREADS * PER_THREAD;

        // ---- 1. Race Condition (Unsafe) ----
        System.out.println("--- 1. WITHOUT Synchronization (Race Condition) ---");
        UnsafeCounter unsafe = new UnsafeCounter();
        runThreads(unsafe::increment, NUM_THREADS, PER_THREAD);
        System.out.println("Expected: " + EXPECTED + " | Got: " + unsafe.getCount() +
                           (unsafe.getCount() != EXPECTED ? "  <-- RACE CONDITION!" : "  (Lucky run)"));

        // ---- 2. Synchronized Keyword ----
        System.out.println("\n--- 2. WITH synchronized keyword ---");
        SynchronizedCounter syncCounter = new SynchronizedCounter();
        runThreads(syncCounter::increment, NUM_THREADS, PER_THREAD);
        System.out.println("Expected: " + EXPECTED + " | Got: " + syncCounter.getCount() +
                           (syncCounter.getCount() == EXPECTED ? "  CORRECT!" : "  WRONG!"));

        // ---- 3. ReentrantLock ----
        System.out.println("\n--- 3. WITH ReentrantLock ---");
        LockCounter lockCounter = new LockCounter();
        runThreads(lockCounter::increment, NUM_THREADS, PER_THREAD);
        System.out.println("Expected: " + EXPECTED + " | Got: " + lockCounter.getCount() +
                           (lockCounter.getCount() == EXPECTED ? "  CORRECT!" : "  WRONG!"));

        // ---- 4. AtomicInteger ----
        System.out.println("\n--- 4. WITH AtomicInteger (lock-free) ---");
        AtomicInteger atomicCounter = new AtomicInteger(0);
        runThreads(atomicCounter::incrementAndGet, NUM_THREADS, PER_THREAD);
        System.out.println("Expected: " + EXPECTED + " | Got: " + atomicCounter.get() +
                           (atomicCounter.get() == EXPECTED ? "  CORRECT!" : "  WRONG!"));

        // ---- 5. Synchronized Bank Account Scenario ----
        System.out.println("\n--- 5. Synchronized BankAccount (Deposit/Withdraw) ---");
        BankAccount account = new BankAccount("Ahmed", 1000.0);

        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.deposit(200);
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        }, "Depositor");

        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(300);
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        }, "Withdrawer");

        depositor.start();
        withdrawer.start();
        depositor.join();
        withdrawer.join();

        System.out.printf("Final balance of %s: $%.2f%n", account.getOwner(), account.getBalance());
    }
}
