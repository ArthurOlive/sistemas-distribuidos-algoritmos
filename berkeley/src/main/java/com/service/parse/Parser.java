package com.service.parse;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Parser {
    
    protected static Object parserJson(String object) {

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