package org.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.HashMap;

public class reqObj<T> {
    interMediateData data;

   public reqObj(inputData data,Class<T> type) throws JsonProcessingException {
        ControllerFlow.takeLog("reqObj:Constructor:JSONDATA");

        this.data =new JSONDATA<>( data.headers,data.content, type);
    }
    public reqObj(inputData data){
       ControllerFlow.takeLog("reqObj:Constructor:textHTML");
        this.data =new textHTML( data.headers,data.content);

    }
    public void setHeader(String key ,String value){
       ControllerFlow.takeLog("reqObj:setHeader");
       ControllerFlow.takeLog("reqObj:setHeader:"+key+":"+value,true);
       data.headers.put(key,value);
    }
    public String getHeader(String key){
       ControllerFlow.takeLog("reqObj:getHeader");
       ControllerFlow.takeLog("reqObj:getHeader:"+key,true);
        return  data.headers.get(key);
    }
    public String getContentType(){
       ControllerFlow.takeLog("reqObj:getContentType");
       ControllerFlow.takeLog("reqObj:getContentType:"+data.contentType,true);
       return  data.contentType;
    }
    public T getJSON(){
       ControllerFlow.takeLog("reqObj:getJSON");
        if("application/json".equals(getContentType())){
            return ((JSONDATA<T>) data).body;

        }else{
            return null;
        }

    }
    public textHTML getText(){
       ControllerFlow.takeLog("reqObj:getText");
        if("text/html".equals(getContentType())){
            return ((textHTML) data);
        }else{
            return null;
        }
    }


}
