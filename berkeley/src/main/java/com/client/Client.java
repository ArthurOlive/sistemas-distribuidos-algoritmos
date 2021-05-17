package com.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.model.Clock;
import com.model.Response;
import com.model.TimerSicronize;
import com.service.parse.ResponseParse;
import com.service.parse.TimeSicronizeParse;
import com.service.utils.ClockTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Client {

    public static void main(String [] args) {

        LocalDateTime timeClock = LocalDateTime.now();
        timeClock = timeClock.plusMinutes(Math.round(30) + 1);

        Clock clock = new Clock();
        clock.setTime(timeClock);

        LocalDateTime date = LocalDateTime.now();

        try {

            System.out.println("Client is online...");
            System.out.println("Initial time client: " + clock.getTime());

            ServerSocket serverSocket = new ServerSocket(3000);

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