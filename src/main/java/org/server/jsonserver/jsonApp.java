package org.server.jsonserver;

import org.server.*;
import org.server.endpoint.endpoint;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;

public class jsonApp {
    ServerSocket server;
    ControllerFlow app;
    public jsonApp() throws IOException {

         app=new ControllerFlow("webserver");
    }
   public  <T,U>void get(String endpoint, requestHandlerJSON<T,U> reqHandler){
        JSONprerequestHandler<T, U> prereqHandler=new JSONprerequestHandler<T,U>(reqHandler);
        endpoint endpointHandler= app.createEndpoint(prereqHandler, requestType.GET);
           app.addEndpoint(endpoint,endpointHandler);
    }
    public <T,U>void post(String endpoint, requestHandlerJSON<T,U> reqHandler){
        JSONprerequestHandler<T, U> prereqHandler=new JSONprerequestHandler<T,U>(reqHandler);
        endpoint endpointHandler= app.createEndpoint(prereqHandler, requestType.POST);
        app.addEndpoint(endpoint,endpointHandler);

    }
    public  <T,U>void put(String endpoint, requestHandlerJSON<T,U> reqHandler){
        JSONprerequestHandler<T, U> prereqHandler=new JSONprerequestHandler<T,U>(reqHandler);
        endpoint endpointHandler= app.createEndpoint(prereqHandler, requestType.PUT);
        app.addEndpoint(endpoint,endpointHandler);
    }
    public <T,U>void delete(String endpoint, requestHandlerJSON<T,U> reqHandler){
        JSONprerequestHandler<T, U> prereqHandler=new JSONprerequestHandler<T,U>(reqHandler);
        endpoint endpointHandler= app.createEndpoint(prereqHandler, requestType.DELETE);
        app.addEndpoint(endpoint,endpointHandler);
    }
    public  void listen(int port) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NullBufferInputException, ClientObjectInitException, InterruptedException {
            server = new ServerSocket(port);
        server.setReuseAddress(true);
        while(true){
            app.handleRequest(server);
        }


    }

}

