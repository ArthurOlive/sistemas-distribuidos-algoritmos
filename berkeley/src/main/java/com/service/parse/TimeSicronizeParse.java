package com.service.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.model.TimerSicronize;

import org.json.simple.JSONObject;
public class TimeSicronizeParse extends Parser{

    public static TimerSicronize parser(String object){
        JSONObject json = (JSONObject) parserJson(object);

        LocalDateTime time = LocalDateTime.parse(String.valueOf(json.get("time")), DateTimeFormatter.ISO_DATE_TIME);
        Long timeDeslocated = Long.parseLong(String.valueOf(json.get("timeDeslocated")));

        TimerSicronize response = new TimerSicronize();
        response.setTime(time);
        response.setTimeDeslocated(timeDeslocated);
        
        return response;
    } 

    public static String stringfy(TimerSicronize object) {
        
        JSONObject json = new JSONObject();

        json.put("time", DateTimeFormatter.ISO_DATE_TIME.format(object.getTime()));
        json.put("timeDeslocated", object.getTimeDesclocated());

        return json.toString();
    }
}