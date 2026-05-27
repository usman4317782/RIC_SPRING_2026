import java.io.IOException;
import java.net.*;

/**
 * Lab Task 4 (Network Programming UDP): UDP Sender & Receiver.
 *
 * HOW TO RUN (in two separate terminals):
 *  Terminal 1 (Receiver): java UDPNetworkDemo receiver
 *  Terminal 2 (Sender):   java UDPNetworkDemo sender
 *
 * UDP is connectionless — no handshake, no guaranteed delivery.
 * It's fast and used for streaming, gaming, DNS, etc.
 *
 * To compile: javac UDPNetworkDemo.java
 */
public class UDPNetworkDemo {

    private static final int    RECEIVER_PORT = 9091;
    private static final String HOST          = "localhost";
    private static final int    BUFFER_SIZE   = 1024;

    // ======================================================
    //  UDP RECEIVER (Server-side)
    // ======================================================
    static void runReceiver() {
        System.out.println("[UDP Receiver] Listening on port " + RECEIVER_PORT + "...");
        try (DatagramSocket socket = new DatagramSocket(RECEIVER_PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int    messagesReceived = 0;

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // blocks until a packet arrives

                String message = new String(packet.getData(), 0, packet.getLength());
                messagesReceived++;

                System.out.printf("[UDP Receiver] Message #%d from %s:%d -> \"%s\"%n",
                    messagesReceived,
                    packet.getAddress().getHostAddress(),
                    packet.getPort(),
                    message);

                // Stop after receiving "END" signal
                if ("END".equals(message.trim())) {
                    System.out.println("[UDP Receiver] END signal received. Stopping receiver.");
                    break;
                }

                // Send ACK back to sender
                String ack      = "ACK#" + messagesReceived + " received: " + message;
                byte[] ackBytes = ack.getBytes();
                DatagramPacket ackPacket = new DatagramPacket(
                    ackBytes, ackBytes.length,
                    packet.getAddress(), packet.getPort());
                socket.send(ackPacket);
            }

            System.out.println("[UDP Receiver] Total messages received: " + messagesReceived);
        } catch (IOException e) {
            System.err.println("[UDP Receiver] Error: " + e.getMessage());
        }
    }

    // ======================================================
    //  UDP SENDER (Client-side)
    // ======================================================
    static void runSender() {
        System.out.println("[UDP Sender] Sending UDP packets to " + HOST + ":" + RECEIVER_PORT);
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(HOST);
            socket.setSoTimeout(2000); // 2 second timeout for ACK

            String[] messages = {
                "Hello via UDP!",
                "UDP is connectionless",
                "Week 14 - UDP Networking",
                "Fast but unreliable delivery",
                "Used in streaming and gaming",
                "END"
            };

            for (String message : messages) {
                byte[]         data   = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address, RECEIVER_PORT);

                socket.send(packet);
                System.out.println("[UDP Sender] Sent: \"" + message + "\"");

                if (!"END".equals(message)) {
                    // Wait for ACK
                    try {
                        byte[]         ackBuf = new byte[BUFFER_SIZE];
                        DatagramPacket ackPkt = new DatagramPacket(ackBuf, ackBuf.length);
                        socket.receive(ackPkt);
                        System.out.println("[UDP Sender] ACK received: " +
                            new String(ackPkt.getData(), 0, ackPkt.getLength()));
                    } catch (SocketTimeoutException e) {
                        System.out.println("[UDP Sender] No ACK received (timeout) - UDP is unreliable!");
                    }
                }

                Thread.sleep(200);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("[UDP Sender] Error: " + e.getMessage());
        }
    }

    // ======================================================
    //  MAIN
    // ======================================================
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  java UDPNetworkDemo receiver   <- Start the UDP receiver");
            System.out.println("  java UDPNetworkDemo sender     <- Start the UDP sender");
            System.out.println();
            System.out.println("Demo: Starting receiver in background, then sender...");

            Thread receiverThread = new Thread(UDPNetworkDemo::runReceiver, "UDP-Receiver");
            receiverThread.setDaemon(true);
            receiverThread.start();

            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            runSender();
            try { Thread.sleep(500); } catch (InterruptedException ignored) {} // wait for last ACK
            return;
        }

        switch (args[0].toLowerCase()) {
            case "receiver": runReceiver(); break;
            case "sender":   runSender();   break;
            default:
                System.out.println("Unknown argument: " + args[0]);
                System.out.println("Use 'receiver' or 'sender'");
        }
    }
}
