package project.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Server extends ThreadBase {
    private int[] ports = { 8080, 8081, 8082, 8083, 8084 };
    private int to;
    private Socket socketFrom;
    static private Socket socketTo;
    static boolean alreadyConectTo = false;

    public Server(int id, int to, Socket socketFrom) {
        super(id);
        this.to = to;
        this.socketFrom = socketFrom;
    }

    @Override
    public void run() {
        try {
            DataInputStream streamIn = new DataInputStream(socketFrom.getInputStream());

            while (socketTo == null) {
                try {
                    socketTo = new Socket("localhost", to);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            DataOutputStream streamOut = new DataOutputStream(socketTo.getOutputStream());

            while (true) {
                byte[] bufferRecive = new byte[streamIn.readInt()];
                streamIn.readFully(bufferRecive);
                message = getMessageByByteMessage(bufferRecive);
                System.out.println(message);
                // callback:
                Thread.sleep(500);

                byte[] bufferSend = buildByteMessage(message + "," + id);
                streamOut.writeInt(bufferSend.length);
                streamOut.write(bufferSend, 0, bufferSend.length);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String getMessageByByteMessage(byte[] buffer) {
        return new String(buffer);
    }
}