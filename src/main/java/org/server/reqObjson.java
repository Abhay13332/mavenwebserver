package org.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class reqObjson{
   HashMap<String,String> headers;
   HashMap<String,String> queries;
   requestType method;
   String endpoint,hostname,useragent;
   private final String body;


    public reqObjson(inputData data){
        headers=data.headers;
        queries=data.queries;
        method=data.method;
        endpoint=data.endpoint;
        hostname=data.headers.get("Host");
        useragent=data.headers.get("User-Agent");
        body=data.content;

    }
    public String getHeader(String key){
        return  headers.get(key);
    }
    public<T> T tojson(Class<T> type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return  mapper.readValue(body,type);
    }
}
