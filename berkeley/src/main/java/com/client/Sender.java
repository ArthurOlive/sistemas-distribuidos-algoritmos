package com.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.model.Clock;
import com.model.Response;
import com.model.TimerSicronize;
import com.service.parse.TimeSicronizeParse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sender implements Runnable{

    private Clock clock;

    public Sender (Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        TimeSicronizeParse parse = new TimeSicronizeParse();
        String msg = null;
        
        while (true) {
            msg = scan.nextLine();

            if (msg.equals("getTime")) {
                TimerSicronize time = new TimerSicronize();
                time.setTime(clock.getTime());
                try {

                    Request.post("getTime", (JSONObject) (new JSONParser()).parse(parse.stringfy(time)));

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid operation!");
            }
        }     
        
    }
    
}
