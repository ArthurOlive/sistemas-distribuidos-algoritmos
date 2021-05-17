package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

import com.model.Clock;
import com.model.Response;
import com.model.TimerSicronize;
import com.service.parse.ResponseParse;
import com.service.parse.TimeSicronizeParse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Middleware implements Runnable{

    private Socket socketClient;
    private Clock clock;
    private String [] resquest;

    public Middleware(Socket socketClient, Clock clock) {
        this.socketClient = socketClient;
        this.clock = clock;
    }

    @Override
    public void run() {
    
        try {
            TimeSicronizeParse parserTime = new TimeSicronizeParse();
            DataInputStream streamIn = new DataInputStream(socketClient.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(socketClient.getOutputStream());

            resquest = streamIn.readUTF().split("::");

            System.out.println(resquest[0]);

            String router = resquest[0];
            JSONObject param = (JSONObject) (new JSONParser()).parse(resquest[1]);

            Response response = new Response(200, new JSONObject());

            switch (router) {
                case "getTime":
                    TimerSicronize timer = parserTime.parser(resquest[1]);

                    System.out.println(timer.getTime());
                    
                    
                    break;
                default:
                    JSONObject data = new JSONObject();
                    data.put("message", "caminho não encontrado");
                    response = new Response(404, data);
                    break;
            }
            
            streamOut.writeUTF(ResponseParse.stringfy(response));
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
