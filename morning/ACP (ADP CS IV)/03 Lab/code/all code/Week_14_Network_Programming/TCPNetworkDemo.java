import java.io.*;
import java.net.*;

/**
 * Lab Task 3 (Part of Network Programming): TCP Client-Server Communication.
 *
 * HOW TO RUN (in two separate terminals, same machine):
 *  Terminal 1 (Server): java TCPNetworkDemo server
 *  Terminal 2 (Client): java TCPNetworkDemo client
 *
 * The server listens for connections, receives messages, and echoes them back.
 * The client connects, sends 5 messages, reads the echoed reply.
 *
 * To compile: javac TCPNetworkDemo.java
 */
public class TCPNetworkDemo {

    private static final int PORT     = 9090;
    private static final String HOST  = "localhost";

    // ======================================================
    //  TCP SERVER
    // ======================================================
    static void runServer() {
        System.out.println("[TCP Server] Starting on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[TCP Server] Waiting for client connection...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[TCP Server] Client connected: " + clientSocket.getInetAddress());

                // Handle each client in a new thread (multi-client support)
                Thread clientHandler = new Thread(() -> handleClient(clientSocket));
                clientHandler.setDaemon(true);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("[TCP Server] Error: " + e.getMessage());
        }
    }

    static void handleClient(Socket socket) {
        try (BufferedReader  reader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter     writer  = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println("Welcome to ACP TCP Server! Type 'bye' to disconnect.");
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("[TCP Server] Received: " + message);
                if ("bye".equalsIgnoreCase(message.trim())) {
                    writer.println("Goodbye! Disconnecting...");
                    break;
                }
                // Echo back with server processing (uppercase)
                writer.println("ECHO >> " + message.toUpperCase());
            }
            System.out.println("[TCP Server] Client disconnected.");
        } catch (IOException e) {
            System.err.println("[TCP Server] Client error: " + e.getMessage());
        }
    }

    // ======================================================
    //  TCP CLIENT
    // ======================================================
    static void runClient() {
        System.out.println("[TCP Client] Connecting to " + HOST + ":" + PORT + "...");
        try (Socket socket             = new Socket(HOST, PORT);
             PrintWriter writer        = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleIn  = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("[TCP Client] Connected!");
            // Print server welcome message
            System.out.println("[TCP Server says] " + reader.readLine());

            // Send pre-defined messages (or use console input)
            String[] messages = {
                "Hello from ACP Client",
                "Java networking is fun!",
                "Week 14 - Network Programming",
                "TCP ensures reliable delivery",
                "bye"
            };

            for (String msg : messages) {
                System.out.println("[TCP Client] Sending: " + msg);
                writer.println(msg);
                String response = reader.readLine();
                if (response != null) {
                    System.out.println("[TCP Server says] " + response);
                }
                Thread.sleep(300);
            }

        } catch (IOException e) {
            System.err.println("[TCP Client] Error: " + e.getMessage());
        } catch (InterruptedException ignored) {}
    }

    // ======================================================
    //  MAIN - Selects server or client mode
    // ======================================================
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  java TCPNetworkDemo server   <- Start the TCP server");
            System.out.println("  java TCPNetworkDemo client   <- Start the TCP client");
            System.out.println();
            System.out.println("Demo: Starting server in background, then client...");

            // Auto-demo: run server in background thread, client in main
            Thread serverThread = new Thread(TCPNetworkDemo::runServer, "TCP-Server");
            serverThread.setDaemon(true);
            serverThread.start();

            try { Thread.sleep(500); } catch (InterruptedException ignored) {} // give server time to start
            runClient();
            return;
        }

        switch (args[0].toLowerCase()) {
            case "server": runServer(); break;
            case "client": runClient(); break;
            default:
                System.out.println("Unknown argument: " + args[0]);
                System.out.println("Use 'server' or 'client'");
        }
    }
}
