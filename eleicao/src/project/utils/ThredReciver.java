package project.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ThredReciver extends ThreadBase {
    private int from;
    private int to;

    public ThredReciver(int id, int from, int to) {
        super(id);
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(from);
            byte[] bufferRecive = new byte[1024];
            do {
                // recive
                DatagramPacket datagramRecive = new DatagramPacket(bufferRecive, bufferRecive.length);
                datagramSocket.receive(datagramRecive);
                bufferRecive = datagramRecive.getData();
                message = getMessageByByteMessage(bufferRecive);

                // callback
                int idCallBack = getIdByByteMessage(bufferRecive);
                if (idCallBack == id) {
                    System.out.println("Menssagem final: " + message);
                } else {
                    String response = actionMessage(message, id);
                    System.out.println(response);

                    byte[] bufferSend = buildByteMessage(idCallBack, response);
                    DatagramPacket datagramSend = new DatagramPacket(bufferSend, bufferSend.length,
                            datagramRecive.getAddress(), to);

                    datagramSocket.send(datagramSend);
                }

            } while (!message.contains("close"));

            datagramSocket.close();
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
