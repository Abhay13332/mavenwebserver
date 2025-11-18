package org.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Text;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;


public class interMediateData{
    String contentType;
    HashMap<String,String> headers;


    interMediateData(String contentType, HashMap<String,String> headers){
        this.contentType=contentType;
        this.headers=headers;
    }
    

}

