package com.service.parse;

import com.model.Response;

import org.json.simple.JSONObject;

public class ResponseParse extends Parser {
    public static Response parser(String object){
        JSONObject json = (JSONObject) parserJson(object);

        int code = Integer.parseInt(String.valueOf(json.get("code")));
        JSONObject data = (JSONObject) parserJson(String.valueOf(json.get("data")));

        Response response = new Response(code,data);
        
        return response;
    } 

    public static String stringfy(Response object) {
        
        JSONObject json = new JSONObject();

        json.put("code", object.getCode());
        json.put("data", object.getData().toString());

        return json.toString();
    }
}