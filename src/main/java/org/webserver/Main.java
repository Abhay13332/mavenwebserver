package org.webserver;

import org.server.*;
import org.server.jsonserver.jsonApp;

import java.lang.reflect.InvocationTargetException;
import java.io.*;
import java.nio.file.Files;


class responseperson extends response{

    @Override
    public void respond(interMediateData Data, PrintWriterwithStream out) throws IOException {

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
    public person(){
    }
}




    void main() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NullBufferInputException, ClientObjectInitException, InterruptedException {

        jsonApp app=new jsonApp();
        app.get("hi",(req,res)->{
            res.json(new person(45,"assdf"));
        });
        app.get(".well-known/appspecific/com.chrome.devtools.json",(req,res)->{
            res.json("{}");
        });
        app.get("hello",(req,res)->{
            person reqBody=req.tojson(person.class);

            res.json(new person(45,"hello"));
        });
        app.get("something",(req,res)->{
            res.customRes((out,utils)->{
                utils.setDefault();
                utils.setToDownload("icon16.png");
                utils.sendstatus(200);
                utils.sendHeader();
                res.setHeaders("Connection","keep-alive");
                Files.copy((new File("public/favicon/icon16.png")).toPath(), out.outputStream);

            });
        });
        app.get("custom-greeting",(req,res)->{
                res.send("<div style=\"background: linear-gradient(90deg,rgba(50, 159, 201, 1) 0%, rgba(87, 199, 133, 1) 50%, rgba(237, 221, 83, 1) 100%); height:40px; width:300px;margin-left:500px;padding:35px; border-radius:20px;\">Welcome to our custom webserver "+req.queries.get("name")+" !</div>");
        });
        app.get("brdestruct",(req,res)->{
            res.customRes((out,utils)->{
                utils.setDefault();
                utils.sendstatus(200);
                utils.sendHeader();

            });
        });

        app.listen(8080);


    }
}

