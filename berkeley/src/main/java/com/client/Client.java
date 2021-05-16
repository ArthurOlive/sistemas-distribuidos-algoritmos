package com.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

        LocalDateTime clock = LocalDateTime.now();
        clock = clock.plusMinutes(Math.round(30) + 1);

        LocalDateTime date = LocalDateTime.now();
        
        System.out.println(clock.atZone(ZoneId.of("Etc/UTC")).toInstant().toEpochMilli());
        System.out.println(date.atZone(ZoneId.of("Etc/UTC")).toInstant().toEpochMilli());

        try {

            System.out.println("Client is online...");
            System.out.println("Initial time client: " + clock);

            ClockTime cronometro    = new ClockTime(clock);
            Thread threadCronometro = new Thread(cronometro);
            threadCronometro.start();

            ServerSocket serverSocket = new ServerSocket(3000);

            while (!serverSocket.isClosed()) {

                Socket clientRequest = serverSocket.accept();

                try {

                    TimerSicronize time = new TimerSicronize();
                    time.setTime(clock);
                    
                    TimeSicronizeParse parse = new TimeSicronizeParse();
                    ResponseParse parseResp = new ResponseParse();
                    
                    Response response = new Response(200, null);
               
                    response.setData((JSONObject) (new JSONParser()).parse(parse.stringfy(time)));

                    DataOutputStream streamOut = new DataOutputStream(clientRequest.getOutputStream());
                    streamOut.writeUTF(parseResp.stringfy(response));

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            threadCronometro.interrupt();
            serverSocket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Internal error: " + e);
        }
        
    }
}