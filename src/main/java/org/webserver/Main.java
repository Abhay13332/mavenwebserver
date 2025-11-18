package org.webserver;

import org.server.*;
import static org.webserver.Main.person;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;


class processperson implements requestHandler<person,person>{


    @Override
    public Pair<person,person> run(reqObj<person> req, resObj<person> res) throws IOException {
        person reqBody=req.getJSON();
        person resBody=res.getJSON();
        IO.println("runn");
        resBody.age=reqBody.age+1;
        resBody.name="sdf";
        return new Pair<person,person>(req,res);
    }
}
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
}



    void main() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ServerSocket server = new ServerSocket(8080);
        ControllerFlow app=new ControllerFlow("webserver");
        Class<person> obj=person.class;
        prerequestHandler<person,person> pr=new prerequestHandler<>(person.class,person.class,new responseperson());
        requestHandler<person,person> intermed=new processperson();
        pr.addHandler(intermed);

        app.addEndpoint("home",app.createEndpoint( pr,requestType.POST));

        while(true){
            app.handleRequest(server);
        }

    }
}
