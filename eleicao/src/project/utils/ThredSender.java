package project.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

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

            Scanner scan = new Scanner(System.in);
            do {
                Thread.sleep(2000);
                message = "Token do processo ";

                byte[] bufferSend = buildByteMessage(id, actionMessage(message, id));

                DatagramPacket datagramSend = new DatagramPacket(bufferSend, bufferSend.length, inetAddress, to);
                datagramSocket.send(datagramSend);

            } while (!message.contains("close"));

            scan.close();
            datagramSocket.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
