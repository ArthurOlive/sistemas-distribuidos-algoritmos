package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.model.Response;
import com.service.parse.ResponseParse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Request {
    
    public static JSONObject post(String router, JSONObject object) throws Exception{        
        Socket connection = new Socket("localhost", 8080);

        DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        DataInputStream input = new DataInputStream(connection.getInputStream());

        output.writeUTF(router + "::" + object.toString());
        String responseMsn = input.readUTF();

        connection.close();
        
        Response response = ResponseParse.parser(responseMsn);        
        if(response.getCode()<300){
            return response.getData();
        } else {
            throw new Exception((String) response.getData().get("message"));
        }
    }

    public static Object parserJson(String object) {

        JSONParser parser = new JSONParser();
		Object parsed = null;
		try {
			parsed = parser.parse(object);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsed;
    }
}
