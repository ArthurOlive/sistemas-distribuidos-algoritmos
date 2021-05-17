package project.utils;

import java.io.IOException;

public class Process {
    public Process(int id, int from, int to, boolean init) throws IOException {
        if(init){
            Thread tSender = new Thread(new ThredSender(id, to));
            tSender.start();
        }

        Thread tRecive = new Thread(new ThredReciver(id, from, to));
        tRecive.start();
    }

    public Process(int id, int from, int to) throws IOException {
        Thread tRecive = new Thread(new ThredReciver(id, from, to));
        tRecive.start();
    }
}
