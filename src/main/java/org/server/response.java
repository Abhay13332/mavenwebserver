package org.server;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public abstract class response{
   protected abstract void respond(interMediateData Data, PrintWriterwithStream out) throws IOException;

    static HashMap<Integer,String> codesstart;

    static{
        codesstart=new HashMap<>();
        codesstart.put(204,"HTTP/1.1 204 No Content");
        codesstart.put(200,"HTTP/1.1 200 OK");
        codesstart.put(201,"HTTP/1.1 201 Created");
        codesstart.put(202,"HTTP/1.1 202 Accepted");
        codesstart.put(205,"HTTP/1.1 205 Reset Content");
        codesstart.put(206,"HTTP/1.1 206 Partial Content");
        codesstart.put(307,"HTTP/1.1 307 Temporary Redirect");
        codesstart.put(308,"HTTP/1.1 308 Permanent Redirect");
        codesstart.put(304,"HTTP/1.1 304 Not Modified");
        codesstart.put(400,"HTTP/1.1 400 Bad Request");
        codesstart.put(401,"HTTP/1.1 401 Unauthorized");
        codesstart.put(403,"HTTP/1.1 403 Forbidden");
        codesstart.put(404,"HTTP/1.1 404 Not Found");
        codesstart.put(405,"HTTP/1.1 405 Method Not Allowed");
        codesstart.put(415,"HTTP/1.1 415 Unsupported Media Type");
        codesstart.put(429,"HTTP/1.1 429 Too Many Requests");
        codesstart.put(451,"HTTP/1.1 451 Unavailible for legal reason");


    }
   static void setDefault(interMediateData Data) throws IOException {
        ControllerFlow.takeLog("resObj:setDefault");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MM yyyy | HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String responedate = sdf.format(new Date());
        Data.headers.putIfAbsent("Date", responedate);
        Data.headers.putIfAbsent("Content-Security-Policy"," default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self'; connect-src 'self'; frame-src 'self'; object-src 'none'");
        Data.headers.putIfAbsent("X-Frame-Options","DENY");
        Data.headers.putIfAbsent("X-XSS-Protection","1; mode=block");
        Data.headers.putIfAbsent("X-Content-Type-Options","nosniff");
        Data.headers.putIfAbsent("Referrer-Policy","no-referrer");
        Data.headers.putIfAbsent("Permissions-Policy","geolocation=(), camera=()");
        Data.headers.putIfAbsent(("Cache-Control"),"public, max-age=0");
        Data.headers.putIfAbsent("Server","Abhay custom Server aaja khelle custom custom custom custom");
        Data.headers.putIfAbsent("Connection","close");
    }
    static void setDefault(HashMap<String,String> headers)  {
        ControllerFlow.takeLog("resObj:setDefault");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MM yyyy | HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String responedate = sdf.format(new Date());
        headers.putIfAbsent("Date", responedate);
        headers.putIfAbsent("Content-Security-Policy"," default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self'; connect-src 'self'; frame-src 'self'; object-src 'none'");
        headers.putIfAbsent("X-Frame-Options","DENY");
        headers.putIfAbsent("X-XSS-Protection","1; mode=block");
        headers.putIfAbsent("X-Content-Type-Options","nosniff");
        headers.putIfAbsent("Referrer-Policy","no-referrer");
        headers.putIfAbsent("Permissions-Policy","geolocation=(), camera=()");
        headers.putIfAbsent(("Cache-Control"),"public, max-age=0");
        headers.putIfAbsent("Server","Abhay custom Server aaja khelle custom custom custom custom");
        headers.putIfAbsent("Connection","close");
    }
    static void start(int StatusCode,interMediateData Data,PrintWriterwithStream output) throws IOException{
        ControllerFlow.takeLog("resObj:start");
        ControllerFlow.takeLog(StatusCode);


        output.println(codesstart.get(StatusCode));
        headerPrint(Data,output);
    }
    protected static void JSONSend(int StatusCode, interMediateData Data, PrintWriterwithStream output) throws IOException {
       ControllerFlow.takeLog("resObj:JSONSend");
        JSONDATA jsonData=(JSONDATA) Data;
        Data.headers.put("Content-Type ","application/json");
        String jsonSerialize=jsonData.toJSON();
        ControllerFlow.takeLog(jsonSerialize);
        Data.headers.put("Content-Length ",String.valueOf( jsonSerialize.length()));
        start(StatusCode,Data,output);

        IO.println(jsonSerialize);
        output.print(jsonSerialize);
        output.flush();

    }

    static void headerPrint(interMediateData Data,PrintWriterwithStream output) throws IOException{
        ControllerFlow.takeLog("resObj:headerPrint");
        for(String key : Data.headers.keySet()){
            output.println(key + " : " + Data.headers.get(key));
            ControllerFlow.takeLog(key + " : " + Data.headers.get(key));

        }
        output.println();
    }


 }





