package project.utils;

import java.time.LocalDateTime;

public abstract class ThreadBase implements Runnable {
    protected int id;

    public ThreadBase(int id) {
        this.id = id;
    }

    protected byte[] buildByteMessage(int id) {
        String mess = "Token recebido do processo " + id + " ["+ LocalDateTime.now() +"]";
        return mess.getBytes();
    }
}