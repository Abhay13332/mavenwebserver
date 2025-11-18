package org.server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControllerFlow {
    endpoints endpoints;

    public static boolean logs=true;
    public static boolean logsObject=true;
    ControllerFlow(endpoints endpoints){
        this.endpoints=endpoints;
    }
    public static void takeLog(Object obj){
        if(logs){

        IO.println( obj);
        }
    }
    public static void takeLog(String obj,boolean logs){
        if(logsObject){
            IO.println(obj);
        }
    }

    public ControllerFlow(String serverType){
        takeLog("serverType="+serverType,true);
        endpoints = new endpoints(serverType);
        takeLog("exiting ControllerFlow constructor");
    }



    public void addEndpoint(String endpoint, endpoint endpointHandler){
        takeLog("ControllerFlow:addEndpoint="+endpoint,true);
        endpoints.createEndpoint(endpoint,endpointHandler);

    }
   public  endpoint createEndpoint(requestProcess reqHandler,requestType method){
        takeLog("ControllerFlow:createEndpoint"+method,true);
        return new endpoint(reqHandler,method);
    }
    public inputData processInputData(BufferedReader clientInput) throws IOException {
        takeLog("ControllerFlow:processInputData");
        return new inputData(clientInput);
    }
    public <T,U> prerequestHandler<T,U> createprerequestHandler(Class<T> typereq,Class<U> typeresp, response response){
          takeLog("ControllerFlow:prerequestHandler");
           return new prerequestHandler<>(typereq,typeresp,response);
    }


    public void handleRequest(ServerSocket server) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      takeLog("ControllerFlow:handleRequest");
       takeLog("ControllerFlow:handleRequest:createClient");
       ClientObj clobj=createClient(server);
       takeLog("ControllerFlow:handleRequest:processInputData");

       inputData data=processInputData(clobj.in);

       if(data==null){
           return;
       }
        takeLog("ControllerFlow:handleRequest:executeEndpoint");
       endpoints.executeEndpoint(data,clobj.out);
       takeLog("ControllerFlow:handleRequest:close client");
        clobj.client.close();
    }

    public ClientObj createClient(ServerSocket server) throws IOException {
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        return new ClientObj(in,out,client);

    }

}
