package org.webserver;

import org.server.*;
import org.server.jsonserver.jsonApp;

import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;



class responseperson extends response{

    @Override
    public void respond(interMediateData Data, PrintWriter out) throws IOException {

        JSONSend(200,Data,out);


    }
}
public class Main{
public static class person {
    public int age;
    public String name;
    public person(int age,String name){
        this.age=age;
        this.name=name;
    }
}




    void main() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NullBufferInputException, ClientObjectInitException, InterruptedException {

        jsonApp app=new jsonApp();
        app.get("hi",(req,res)->{
            res.json(new person(45,"assdf"));
        });
        app.get("hello",(req,res)->{
            person reqBody=req.tojson(person.class);
            res.json(new person(45,"hello"));
        });
        app.listen(8080);


    }
}

