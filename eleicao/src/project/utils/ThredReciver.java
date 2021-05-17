package project.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
                System.out.println(getMessageByByteMessage(bufferRecive));

                // callback
                Thread.sleep(1000);           
                byte[] bufferSend = buildByteMessage(id);
                DatagramPacket datagramSend = new DatagramPacket(bufferSend, bufferSend.length,
                        datagramRecive.getAddress(), to);

                datagramSocket.send(datagramSend);

            } while (true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private String getMessageByByteMessage(byte[] buffer) {
        return new String(buffer);
    }
}
