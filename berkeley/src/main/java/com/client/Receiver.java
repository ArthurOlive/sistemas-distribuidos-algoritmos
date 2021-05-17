package com.client;

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

public class Receiver implements Runnable {

    private Socket connection;
    private Clock clock;
    private String [] resquest;

    public Receiver(Socket connection, Clock clock) {
        this.connection = connection;
        this.clock = clock;
    }

    @Override
    public void run() {

        TimeSicronizeParse parse = new TimeSicronizeParse();
        ResponseParse parseResp = new ResponseParse();

        try {
            DataInputStream streamIn = new DataInputStream(connection.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(connection.getOutputStream());

            resquest = streamIn.readUTF().split("::");

            System.out.println(resquest[0]);

            String router = resquest[0];
            JSONObject param = (JSONObject) (new JSONParser()).parse(resquest[1]);
            
            Response response = null;
        
        
            switch (router) {
                case "getTime":

                    TimerSicronize time = new TimerSicronize();
                    time.setTime(clock.getTime());
                    streamOut.writeUTF(parse.stringfy(time));
                    break;

                case "setTime":

                    break;

                default:
                    JSONObject data = new JSONObject();
                    data.put("message", "caminho não encontrado");
                    response = new Response(404, data);
                    break;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
