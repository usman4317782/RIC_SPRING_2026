import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

/**
 * Task 3: Develop a simple TCP client-server chat application (Client).
 * 
 * Run TCPServer first, then execute this program.
 */
public class TCPClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("=== TCP Client Console ===");
        System.out.println("Attempting connection to " + HOST + ":" + PORT + "...");

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            System.out.println("Connected to TCP Server!");
            
            // Send test message
            String clientMessage = "Hello from the client! Testing socket communication.";
            out.println(clientMessage);
            System.out.println("Sent to Server: \"" + clientMessage + "\"");
            
            // Read server reply
            String serverReply = in.readLine();
            System.out.println("Received from Server: \"" + serverReply + "\"");
            
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            System.out.println("Make sure TCPServer is running before launching the client.");
        }
        System.out.println("Client disconnected.");
    }
}
