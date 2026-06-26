import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;

/**
 * Task 4: Implement a UDP time server-client program (Client).
 * 
 * Sends a query datagram to the server on port 5001 and receives the system time string.
 */
public class UDPTimeClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("=== UDP Time Client Console ===");
        System.out.println("Sending time inquiry datagram to " + HOST + ":" + PORT + "...");

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(HOST);
            byte[] outgoingData = "TIME_REQUEST".getBytes();

            // 1. Send query datagram to server
            DatagramPacket sendPacket = new DatagramPacket(outgoingData, outgoingData.length, serverAddress, PORT);
            socket.send(sendPacket);
            System.out.println("Datagram request sent.");

            // 2. Receive reply datagram from server
            byte[] incomingBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(incomingBuffer, incomingBuffer.length);
            
            // Set socket timeout to 3 seconds so client doesn't hang infinitely if server is down
            socket.setSoTimeout(3000);
            
            System.out.println("Awaiting datagram reply...");
            socket.receive(receivePacket); // Blocks until reply arrives

            String serverTime = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server Response (System Time): " + serverTime);

        } catch (IOException e) {
            System.out.println("UDP Client error: " + e.getMessage());
            System.out.println("Make sure UDPTimeServer is running before executing client.");
        }
        System.out.println("UDP Client closed.");
    }
}
