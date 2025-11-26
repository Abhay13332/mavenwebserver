package org.server.endpoint;

import org.server.*;

import java.io.IOException;
import org.server.PrintWriterwithStream;
import java.lang.reflect.InvocationTargetException;

public class endpoint {
    requestProcess getEndpointHandler, postEndpointHandler, putEndpointHandler, deleteEndpointHandler;
    public endpoint(requestProcess reqHandler, requestType method){
        ControllerFlow.takeLog("endpoint:Constructor");
        ControllerFlow.takeLog("\tendpoint:"+method);
        switch (method){
            case GET -> getEndpointHandler = reqHandler;
            case POST -> postEndpointHandler = reqHandler;
            case PUT -> putEndpointHandler = reqHandler;
            case DELETE -> deleteEndpointHandler = reqHandler;
        }
    }

    public void addMethod(prerequestHandler<Object,Object> reqHandler, requestType method){
        ControllerFlow.takeLog("endpoint:addMethod");
        switch (method){
            case GET -> {
                if(getEndpointHandler !=null){
                    throw new alreadyCreated("GET");
                }
                getEndpointHandler = reqHandler;

            }
            case POST -> {
                if(postEndpointHandler !=null){
                    throw new alreadyCreated("POST");
                }
                postEndpointHandler = reqHandler;
            }
            case PUT -> {
                if(putEndpointHandler !=null){
                    throw new alreadyCreated("PUT");
                }
                putEndpointHandler = reqHandler;
            }
            case DELETE -> {
                if(deleteEndpointHandler !=null){
                    throw new alreadyCreated("DELETE");
                }
                deleteEndpointHandler = reqHandler;
            }
        }
    }
    public  void execute(requestType method, inputData data, PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ControllerFlow.takeLog("endpoint:execute");
        switch (method){
            case GET -> this.get(data,out);
            case POST -> this.post(data,out);
            case PUT -> this.put(data,out);
            case DELETE -> this.delete(data,out);
        }
    }
    public  void get(inputData data, PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      ControllerFlow.takeLog("endpoint:get");
       if(getEndpointHandler !=null){
           getEndpointHandler.execute(data,out);
       }
    }
    public void post(inputData data,PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ControllerFlow.takeLog("endpoint:post");
        if(postEndpointHandler !=null){
            postEndpointHandler.execute(data,out);
        }
    }
    public void put(inputData data,PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ControllerFlow.takeLog("endpoint:put");
        if(putEndpointHandler !=null){
            putEndpointHandler.execute(data,out);
        }
    }
    public  void delete(inputData data,PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       ControllerFlow.takeLog("endpoint:delete");
        if(deleteEndpointHandler !=null){
            deleteEndpointHandler.execute(data,out);
        }
    }
}
