package project.utils;

import java.nio.ByteBuffer;

public abstract class ThreadBase implements Runnable {
    public final String ELEICAO = "ELEICAO";
    public final String COORDENADOR = "COORDENADOR";
    public final String DIV = "%";
    protected String message;
    protected int id;

    public ThreadBase(int id) {
        this.id = id;
    }

    protected byte[] buildByteMessage(String msn) {
        return msn.getBytes();
    }
}