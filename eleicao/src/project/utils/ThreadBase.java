package project.utils;

import java.nio.ByteBuffer;

public abstract class ThreadBase implements Runnable {
    protected String message;
    protected int id;

    public ThreadBase(int id) {
        this.id = id;
    }

    protected String actionMessage(String msn, int id) {
        String finalMsn = "";
        try {
            int num = Integer.parseInt(msn) + id;
            finalMsn = num + "";

        } catch (NumberFormatException e) {
            finalMsn = msn + id;
        }
        return finalMsn;
    }

    protected byte[] buildByteMessage(int id, String msn) {

        byte[] idBytes = ByteBuffer.allocate(4).putInt(id).array();
        byte[] messageBytes = msn.getBytes();

        byte[] buffer = new byte[idBytes.length + messageBytes.length];

        for (int i = 0; i < buffer.length; i++) {
            if (i < 4)
                buffer[i] = idBytes[i];
            else
                buffer[i] = messageBytes[i - 4];
        }

        return buffer;
    }
}