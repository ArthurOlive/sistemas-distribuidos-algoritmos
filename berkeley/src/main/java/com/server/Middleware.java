package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class Middleware implements Runnable{

    private Socket socketClient;
    private LocalDateTime clock;
    private String[] resquest;

    public Middleware(Socket socketClient, LocalDateTime clock) {
        this.socketClient = socketClient;
        this.clock = clock;
    }

    @Override
    public void run() {
    
        try {
            DataInputStream streamIn = new DataInputStream(socketClient.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(socketClient.getOutputStream());


        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
