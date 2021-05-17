package project.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThredSender extends ThreadBase {
    private int to;

    public ThredSender(int id, int to) {
        super(id);
        System.out.println("ID: " + id);
        this.to = to;
    }

    @Override
    public void run() {
        try {

            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");

            byte[] bufferSend = buildByteMessage(id);

            DatagramPacket datagramSend = new DatagramPacket(bufferSend, bufferSend.length, inetAddress, to);
            datagramSocket.send(datagramSend);

            datagramSocket.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
