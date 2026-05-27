import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Lab Task 2: Implement Runnable interface to create threads.
 *
 * Demonstrates:
 *  - Creating threads using Runnable (preferred over extending Thread)
 *  - Using Thread constructor with Runnable
 *  - Using ExecutorService (thread pool) for managing multiple threads
 *  - Daemon threads
 *
 * To compile: javac RunnableThreadDemo.java
 * To run:     java RunnableThreadDemo
 */
public class RunnableThreadDemo {

    // ---- Runnable: Download task simulation ----
    static class DownloadTask implements Runnable {
        private final String fileName;
        private final int    sizeKB;

        public DownloadTask(String fileName, int sizeKB) {
            this.fileName = fileName;
            this.sizeKB   = sizeKB;
        }

        @Override
        public void run() {
            String tName = Thread.currentThread().getName();
            System.out.println("[" + tName + "] Starting download: " + fileName + " (" + sizeKB + " KB)");
            int downloaded = 0;
            while (downloaded < sizeKB) {
                int chunk = Math.min(100, sizeKB - downloaded);
                downloaded += chunk;
                System.out.printf("[%s] Downloading %s: %d/%d KB%n", tName, fileName, downloaded, sizeKB);
                try { Thread.sleep(150); } catch (InterruptedException e) {
                    System.out.println("[" + tName + "] Download interrupted!");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("[" + tName + "] Finished: " + fileName);
        }
    }

    // ---- Runnable: Log monitor (daemon) ----
    static class LogMonitor implements Runnable {
        @Override
        public void run() {
            int tick = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("[LogMonitor-Daemon] Heartbeat tick #" + (++tick));
                try { Thread.sleep(500); } catch (InterruptedException e) {
                    System.out.println("[LogMonitor-Daemon] Shutting down...");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("  Runnable Thread Demo - ACP Week 13              ");
        System.out.println("=================================================\n");

        // ---- Method 1: Thread(Runnable r) constructor ----
        System.out.println("--- Method 1: Thread(Runnable) Constructor ---");
        Runnable r1 = new DownloadTask("report.pdf", 250);
        Runnable r2 = new DownloadTask("image.png",  150);

        Thread thread1 = new Thread(r1, "Downloader-1");
        Thread thread2 = new Thread(r2, "Downloader-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        // ---- Method 2: Lambda Runnable ----
        System.out.println("\n--- Method 2: Lambda as Runnable ---");
        Thread lambdaThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("[Lambda-Thread] Step " + i);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        }, "Lambda-Thread");
        lambdaThread.start();
        lambdaThread.join();

        // ---- Method 3: ExecutorService (Thread Pool) ----
        System.out.println("\n--- Method 3: ExecutorService (Thread Pool of 3) ---");
        ExecutorService pool = Executors.newFixedThreadPool(3);

        String[] files = {"video.mp4", "audio.mp3", "notes.docx", "data.csv", "archive.zip"};
        int[]    sizes = {500, 200, 100, 80, 350};

        for (int i = 0; i < files.length; i++) {
            pool.submit(new DownloadTask(files[i], sizes[i]));
        }

        pool.shutdown(); // no more tasks accepted
        pool.awaitTermination(30, TimeUnit.SECONDS);

        // ---- Daemon Thread ----
        System.out.println("\n--- Daemon Thread Example ---");
        Thread daemon = new Thread(new LogMonitor(), "LogMonitor-Daemon");
        daemon.setDaemon(true); // will auto-stop when main thread ends
        daemon.start();

        Thread.sleep(1200); // let daemon run for a bit
        System.out.println("Main thread ending. Daemon thread will stop automatically.");
    }
}
