package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.time.ZoneOffset;

import com.model.Clock;
import com.model.TimerSicronize;
import com.service.parse.TimeSicronizeParse;
import com.service.utils.ClockTime;

public class Server {

    public static void main(String [] args) {
        
        LocalDateTime time = LocalDateTime.now();
        time = time.plusMinutes(Math.round(30) + 1);

        Clock clock = new Clock();
        clock.setTime(time);

        try {

            System.out.println("Server is online...");
            System.out.println("Initial time server: " + clock.getTime());

            ClockTime cronometro    = new ClockTime(clock);
            Thread threadCronometro = new Thread(cronometro);
            threadCronometro.start();

            ServerSocket serverSocket = new ServerSocket(8080);

            while (!serverSocket.isClosed()) {

                Socket clientRequest = serverSocket.accept();
                Middleware midd = new Middleware(clientRequest, clock);
    
                Thread thread = new Thread(midd);
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