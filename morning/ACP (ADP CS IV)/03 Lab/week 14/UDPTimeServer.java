import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import java.io.IOException;

/**
 * Task 4: Implement a UDP time server-client program (Server).
 * 
 * Binds on port 5001, listens for incoming packet requests, and replies 
 * with the server system time.
 */
public class UDPTimeServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("=== UDP Time Server Console ===");
        System.out.println("Listening for Datagram packets on port " + PORT + "...");

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] incomingBuffer = new byte[1024];

            while (true) {
                // Prepare packet to receive data
                DatagramPacket receivePacket = new DatagramPacket(incomingBuffer, incomingBuffer.length);
                socket.receive(receivePacket); // Blocks until a packet arrives
                
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Received time query from client: " + clientAddress + ":" + clientPort);

                // Construct system time string response
                String timeResponse = new Date().toString();
                byte[] outgoingData = timeResponse.getBytes();

                // Send response packet back to sender address/port
                DatagramPacket sendPacket = new DatagramPacket(outgoingData, outgoingData.length, clientAddress, clientPort);
                socket.send(sendPacket);
                System.out.println("Sent time string: \"" + timeResponse + "\"");
            }
        } catch (IOException e) {
            System.out.println("UDP Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
