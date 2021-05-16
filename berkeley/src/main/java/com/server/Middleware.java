package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

import com.model.TimerSicronize;
import com.service.parse.TimeSicronizeParse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Middleware implements Runnable{

    private Socket socketClient;
    private LocalDateTime clock;
    private String resquest;

    public Middleware(Socket socketClient, LocalDateTime clock) {
        this.socketClient = socketClient;
        this.clock = clock;
    }

    @Override
    public void run() {
    
        try {
            DataInputStream streamIn = new DataInputStream(socketClient.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(socketClient.getOutputStream());

            resquest = streamIn.readUTF();

            JSONObject param = (JSONObject) (new JSONParser()).parse(resquest);
            
            TimerSicronize time = (new TimeSicronizeParse()).parser(param.get("data").toString());

            //streamOut.writeUTF(ResponseParse.stringfy(response));
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
