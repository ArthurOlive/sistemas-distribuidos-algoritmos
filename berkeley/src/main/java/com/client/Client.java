package com.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.model.Clock;
import com.service.utils.ClockTime;

import java.util.Random;

public class Client implements Runnable {

    private int port;

    public Client (int port) {
        this.port = port;
    }

    public void run () {

        Random gerador = new Random(port);

        LocalDateTime timeClock = LocalDateTime.now();
        timeClock = timeClock.plusMinutes(gerador.nextInt(30) + 1);

        Clock clock = new Clock();
        clock.setTime(timeClock);

        try {

            System.out.println("Client is online...");
            System.out.println("Initial time client: " + clock.getTime());

            ServerSocket serverSocket = new ServerSocket(port);

            ClockTime cronometro    = new ClockTime(clock);
            Thread threadCronometro = new Thread(cronometro);
            threadCronometro.start();

            Sender sender = new Sender(clock);
            Thread threadSender = new Thread(sender);
            threadSender.start();

            while (!serverSocket.isClosed()) {

                Socket clientRequest = serverSocket.accept();
                Receiver receiver = new Receiver(clientRequest, clock);

                Thread thread = new Thread(receiver);
                thread.start();
                
            }

            threadCronometro.interrupt();
            serverSocket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Internal error: " + e);
        }
        
    }
}