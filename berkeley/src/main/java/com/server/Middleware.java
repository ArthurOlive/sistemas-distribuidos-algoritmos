package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Cache cache;
    
    public Middleware(Socket socketClient, Clock clock, Cache cache) {
        this.socketClient = socketClient;
        this.clock = clock;
        this.cache = cache;
    }

    @Override
    public void run() {
    
        try {
            System.out.println("Receive Request " + clock.getTime());

            TimeSicronizeParse parserTime = new TimeSicronizeParse();
            DataInputStream streamIn = new DataInputStream(socketClient.getInputStream());
            DataOutputStream streamOut = new DataOutputStream(socketClient.getOutputStream());

            resquest = streamIn.readUTF().split("::");

            String router = resquest[0];
            JSONObject param = (JSONObject) (new JSONParser()).parse(resquest[1]);

            Response response = new Response(200, new JSONObject());

            switch (router) {
                case "getTime":

                    Map<Integer, TimerSicronize> timers = new HashMap<>();

                    for (int i = 0; i < cache.getClients().size(); i ++) {
                        try {

                            JSONObject resp = Request.post("getTime", cache.getClients().get(i), new JSONObject());
                            TimerSicronize timer = parserTime.parser(resp.toJSONString());

                            timers.put(cache.getClients().get(i), timer);

                        } catch (Exception e) {
                            System.out.println("Connection with " + cache.getClients().get(i) + " was refused.");
                        }
                    }                
                    
                    long clock_time = clock.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(); 
                    int size_clock = 1;
                    long time_milisegs = clock_time;

                    
                    for (int i = 0; i < cache.getClients().size(); i++) {

                        if (timers.containsKey(cache.getClients().get(i))) {

                            time_milisegs += timers.get(cache.getClients().get(i)).getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                            size_clock++;

                        }
                    }

                    long med = time_milisegs/size_clock;
                    long deslocate = med - clock_time;

                    clock.setTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + deslocate), ZoneId.systemDefault()));

                    for (int i = 0; i < cache.getClients().size(); i++) {

                        if (timers.containsKey(cache.getClients().get(i))) {

                            System.out.println(timers.get(cache.getClients().get(i)).getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

                            timers.get(cache.getClients().get(i)).setTimeDeslocated(med - timers.get(cache.getClients().get(i)).getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                            
                        }
                    }

                    for (int i = 0; i < cache.getClients().size(); i ++) {
                        
                        if (timers.containsKey(cache.getClients().get(i))) {

                            String msg = parserTime.stringfy(timers.get(cache.getClients().get(i)));
                            System.out.println(msg);

                            Request.post("setTime", cache.getClients().get(i), (JSONObject) (new JSONParser()).parse(msg));
                        }
                    }
                    
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
