package org.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class resObjson{
    HashMap<String,String> headers;
    response respond;
    PrintWriterwithStream out;
    int status=200;
    public resObjson(PrintWriterwithStream out){
        respond =new response() {
            @Override
            protected void respond(interMediateData Data, PrintWriterwithStream out) throws IOException {
                response.setDefault(Data);
                JSONSend(status,Data,out);

            }
        };
        this.out=out;
        headers=new HashMap<>();



    }
    public void setHeaders(String key,String value){
        headers.put(key,value);
    }
    public void send(String text) throws IOException {
        interMediateData Data=new JSONDATA<>(headers,text);
       respond.respond(Data,out);
    }
    public void json(String text) throws IOException {
        respond.respond(new JSONDATA<>(headers,text), out);
    }
    public void customRes(runnableResponse res) throws IOException {
        res.respond( out,new resUtil(out,this));
    }
    public <T>void json(T obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String val=mapper.writeValueAsString(obj);
        respond.respond(new JSONDATA<>(headers,val), out);

    }
    resObjson status(int status){
        this.status =status;
        return this;
    }
    void redirect(String text) throws IOException {

    }
}
