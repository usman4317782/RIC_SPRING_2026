import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Task 3: Develop a simple TCP client-server chat application (Server).
 * 
 * Run this Server class first, then execute TCPClient.
 */
public class TCPServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("=== TCP Server Console ===");
        System.out.println("Starting server and listening on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Wait/block until a client connects
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                
                System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
                
                // Read client message
                String clientMessage = in.readLine();
                System.out.println("Received from Client: \"" + clientMessage + "\"");
                
                // Send response
                String serverResponse = "Hello Client! I received your message: \"" + clientMessage + "\" successfully.";
                out.println(serverResponse);
                System.out.println("Replied to Client: \"" + serverResponse + "\"");
                
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Server shutting down.");
    }
}
