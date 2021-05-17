package project.utils;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends ThreadBase {

    private int to;

    public Client(int id, int to) {
        super(id);
        System.out.println("ID: " + id);
        this.to = to;
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            while (socket == null) {
                try {
                    socket = new Socket("localhost", to);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            while (!message.contains("close")) {
                System.out.println("Digite uma menssagem: ");
                if (scan.hasNextLine()) {
                    message = scan.nextLine();

                    byte[] bufferSend = buildByteMessage(id, actionMessage(message, id));
                    streamOut.writeInt(bufferSend.length);
                    streamOut.write(bufferSend, 0, bufferSend.length);
                }
            }
            scan.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
