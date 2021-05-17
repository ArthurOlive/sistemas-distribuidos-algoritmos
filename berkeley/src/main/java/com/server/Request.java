package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Request {
    public static JSONObject post(String router, JSONObject param) {
        Socket socketTo;
        try {
            socketTo = new Socket("localhost", 8082);
            DataOutputStream streamOut = new DataOutputStream(socketTo.getOutputStream());
            DataInputStream streamIn = new DataInputStream(socketTo.getInputStream());
            streamOut.writeUTF(router + "::" + param.toString());
            String response = streamIn.readUTF();

            return (JSONObject) new JSONParser().parse(response);            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
