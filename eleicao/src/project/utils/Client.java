package project.utils;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends ThreadBase {

    private int to;

    public Client(int id, int to) {
        super(id);
        this.to = to;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", to);
            DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
            message = ELEICAO + DIV + id;

            byte[] bufferSend = buildByteMessage(message);
            streamOut.writeInt(bufferSend.length);
            streamOut.write(bufferSend, 0, bufferSend.length);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}