package org.server;

import org.server.endpoint.endpoint;
import org.server.endpoint.endpoints;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControllerFlow {
    endpoints endpoints;

    public static boolean logs=false;
    public static boolean logsObject=true;
    ControllerFlow(endpoints endpoints){
        this.endpoints=endpoints;
    }
    public static void takeLog(Object obj){
        if(logs){

        IO.println( obj);
        }
    }
    public static void takeLog(Object obj,boolean logs){
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
        endpoint=endpoint.transform((orig )->orig.startsWith("/")?orig.substring(1):orig );
        takeLog("ControllerFlow:addEndpoint="+endpoint,true);
        endpoints.createEndpoint(endpoint,endpointHandler);

    }
   public  endpoint createEndpoint(requestProcess reqHandler,requestType method){
        takeLog("ControllerFlow:createEndpoint"+method,true);
        return new endpoint(reqHandler,method);
    }
    public inputData processInputData(BufferedReader clientInput) throws IOException, NullBufferInputException {
        takeLog("ControllerFlow:processInputData");

        return new inputData(clientInput);
    }
    public <T,U> prerequestHandler<T,U> createprerequestHandler(Class<T> typereq,Class<U> typeresp, response response){
          takeLog("ControllerFlow:prerequestHandler");
           return new prerequestHandler<>(typereq,typeresp,response);
    }


    public void handleRequest(ServerSocket server) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NullBufferInputException, ClientObjectInitException, InterruptedException {
      takeLog("ControllerFlow:handleRequest");
       takeLog("ControllerFlow:handleRequest:createClient");
       ClientObj clobj=createClient(server);
       takeLog("ControllerFlow:handleRequest:processInputData");
        IO.println(clobj.in);
       try {
            inputData data = processInputData(clobj.in);

            if (data == null) {
                return;
            }
            takeLog("ControllerFlow:handleRequest:executeEndpoint");
            endpoints.executeEndpoint(data, clobj.out);
            takeLog("ControllerFlow:handleRequest:close client");
        }catch (Exception e){
           IO.println(e.getMessage());
       }finally {
        clobj.client.close();

       }
    }

    public ClientObj createClient(ServerSocket server) throws IOException, ClientObjectInitException, InterruptedException {
        Socket client = server.accept();
        client.setSoTimeout(10000); // 10 second timeout
        client.setTcpNoDelay(true);
        InputStream stream =client.getInputStream();
       if(stream==null){
           throw  new ClientObjectInitException("stream is null");
       }
        OutputStream clos=client.getOutputStream();
        PrintWriterwithStream out = new PrintWriterwithStream(new BufferedWriter(new OutputStreamWriter(clos)), true,clos);
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        return new ClientObj(in,out,client);




    }

}
