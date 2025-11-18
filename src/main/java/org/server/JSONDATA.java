package org.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class JSONDATA<T> extends interMediateData{

    T body;
    public JSONDATA(HashMap<String,String> headers, String content, Class<T> type) throws JsonProcessingException {
        super("application/json",headers);
        jsonParse(content,type);


    }
    public JSONDATA(HashMap<String,String> headers,T init) throws JsonProcessingException {
        super("application/json",headers);
        body=init;
    }
    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(body);

    }

     public void jsonParse(String text,Class<T> type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        body=  mapper.readValue(text,type);

    }


}
