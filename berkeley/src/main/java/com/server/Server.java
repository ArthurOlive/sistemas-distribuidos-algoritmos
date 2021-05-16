package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;

import com.model.TimerSicronize;
import com.service.parse.TimeSicronizeParse;

public class Server {

    public static void main(String [] args) {
        
        TimerSicronize time = new TimerSicronize();
        TimeSicronizeParse parse = new TimeSicronizeParse();
        LocalDateTime date = LocalDateTime.now();
        
        //System.out.println(date.atZone(ZoneId.of("Etc/UTC")).toInstant().toEpochMilli());
        //System.out.println(System.currentTimeMillis());

        //time.setTime(date);

        //System.out.println(parse.stringfy(time));

        try {
            
            System.out.println("Server is online...");
            ServerSocket serverSocket = new ServerSocket(8080);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}