package project.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Server extends ThreadBase {
    private int to;
    private Socket socketFrom;
    static private Socket socketTo;

    public Server(int id, int to, Socket socketFrom) {
        super(id);
        this.to = to;
        this.socketFrom = socketFrom;
        System.out.println("PROCESS " + id);
    }

    @Override
    public void run() {
        DataInputStream streamIn = null;

        DataOutputStream streamOut = null;
        try {
            streamIn = new DataInputStream(socketFrom.getInputStream());
            streamOut = tryConnect();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            try {
                byte[] bufferRecive = new byte[streamIn.readInt()];
                streamIn.readFully(bufferRecive);
                message = getMessageByByteMessage(bufferRecive);
                System.out.println(message);

                message = handleMessage(message);

                try {
                    // callback:
                    Thread.sleep(500);

                    byte[] bufferSend = buildByteMessage(message);
                    streamOut.writeInt(bufferSend.length);
                    streamOut.write(bufferSend, 0, bufferSend.length);
                } catch (Exception e) {
                    tryReconnect(streamOut);
                }
            } catch (SocketException e) {
                break;
            } catch (IOException e) {
                // e.printStackTrace();
            }

        }
    }

    private String getMessageByByteMessage(byte[] buffer) {
        return new String(buffer);
    }

    private DataOutputStream tryConnect() throws IOException {
        while (socketTo == null) {
            try {
                socketTo = new Socket("localhost", to);
            } catch (Exception e) {
                to++;
                if (to > 8084)
                    to = 8080;
            }
        }
        System.out.println("choice port " + to);
        if (to == socketFrom.getPort())
            return null;
        return new DataOutputStream(socketTo.getOutputStream());
    }

    private void tryReconnect(DataOutputStream streamOut) throws IOException {
        socketTo = null;
        streamOut = tryConnect();
        if (streamOut != null) {
            message = ELEICAO + DIV + id;
            byte[] bufferSend = buildByteMessage(message);
            streamOut.writeInt(bufferSend.length);
            streamOut.write(bufferSend, 0, bufferSend.length);

        } else
            System.err.println("Erro de conexão 2");
    }

    private String handleMessage(String message) {
        var messDiv = message.split(DIV);

        if (messDiv[0].equals(ELEICAO)) {
            boolean find = false;
            var ids = messDiv[1].split(",");
            int biggerId = id;
            for (int i = 0; i < ids.length; i++) {
                int val = Integer.parseInt(ids[i]);
                if (val > biggerId)
                    biggerId = val;
                if (val == id)
                    find = true;
            }

            if (find) {
                return COORDENADOR + DIV + biggerId;
            } else
                return message + "," + id;
        }
        return message;

    }
}