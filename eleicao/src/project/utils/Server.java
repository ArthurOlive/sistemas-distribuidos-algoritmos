package project.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Server extends ThreadBase {
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

            while (!message.contains("close")) {
                byte[] bufferRecive = new byte[streamIn.readInt()];
                streamIn.readFully(bufferRecive);
                message = getMessageByByteMessage(bufferRecive);

                // callback:
                int idCallBack = getIdByByteMessage(bufferRecive);
                if (idCallBack == id) {
                    System.out.println("Menssagem final: " + message);
                } else {
                    String response = actionMessage(message, id);
                    System.out.println(response);

                    byte[] bufferSend = buildByteMessage(idCallBack, response);
                    streamOut.writeInt(bufferSend.length);
                    streamOut.write(bufferSend, 0, bufferSend.length);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private int getIdByByteMessage(byte[] buffer) {
        return ByteBuffer.wrap(buffer).getInt();
    }

    private String getMessageByByteMessage(byte[] buffer) {
        int realSize = 0;
        for (int i = 0; i < buffer.length - 4 && buffer[i + 4] != 0; i++) {
            realSize++;
        }

        byte[] bytesMessage = new byte[realSize];

        for (int i = 0; i < bytesMessage.length; i++) {
            bytesMessage[i] = buffer[i + 4];
        }

        return new String(bytesMessage);
    }
}
