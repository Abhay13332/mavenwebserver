package org.server;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class resObj<T>{
    interMediateData data;
    public resObj(Class<T> type) throws JsonProcessingException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            ControllerFlow.takeLog("resObj:Constructor:JSONDATA");
            HashMap<String,String> headers=new HashMap<>();
            headers.put("Content-Type","application/json");
            IO.println(type);
            data =new JSONDATA<>(headers,(type.getConstructor()).newInstance());
  }
    public resObj(){
        ControllerFlow.takeLog("resObj:Constructor:textHTML");

        HashMap<String,String> headers=new HashMap<>();
        headers.put("Content-Type","text/html");
        data =new textHTML(headers,"");
    }
    public void setHeader(String key ,String value){
        ControllerFlow.takeLog("resObj:setHeader");
        ControllerFlow.takeLog("resObj:setHeader:"+key+":"+value,true);

        data.headers.put(key,value);
    }
    public String getHeader(String key){
      ControllerFlow.takeLog("resObj:getHeader");
      ControllerFlow.takeLog("resObj:setHeader:"+key,true);

        return  data.headers.get(key);
    }
    public String getContentType(){
        return  data.contentType;
    }
    public T getJSON(){
        ControllerFlow.takeLog("resObj:getJSON");
        if("application/json".equals(getContentType())){
           return ((JSONDATA<T>) data).body;

        }else{
            return null;
        }

    }
    public textHTML getText(){
        ControllerFlow.takeLog("resObj:getText");
        if("text/html".equals(getContentType())){
           return ((textHTML) data);
        }else{
            return null;
        }
    }
}
