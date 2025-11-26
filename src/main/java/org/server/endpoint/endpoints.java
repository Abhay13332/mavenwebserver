package org.server.endpoint;

import org.server.*;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
class alreadyCreated extends RuntimeException {
    alreadyCreated(String method){
        super(method+":this method is added previously");
    }
}


public class endpoints {
   public HashMap<String, endpoint> endpointrequestHandlers ;
    public ArrayList<specialEndpoints> endpoints  ;
    public endpoints(String mode){
        ControllerFlow.takeLog("endpoints:Constructor:mode:"+mode);
        endpointrequestHandlers = new HashMap<>();
        endpoints = new ArrayList<>();
        if(mode.equals("webserver")){

        endpoints.add(new starterPage());
        }else if(mode.equals("Api")){

        }
        endpoints.add(new resources());

    }

    public void createEndpoint(String endpoint,endpoint endpointHandler){
        ControllerFlow.takeLog("endpoints:createEndpoint:"+endpoint);
        endpointrequestHandlers.put(endpoint,endpointHandler);


    }
    public void executeEndpoint(inputData data, PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ControllerFlow.takeLog("endpoints:executeEndpoint");
        ControllerFlow.takeLog("endpoints:executeEndpoint:check for special endpoints");
             ControllerFlow.takeLog("end" +data.endpoint,true);
        for(specialEndpoints endpointHandler:endpoints){
         if(endpointHandler.checkandrun(data.endpoint,data,out)){
             ControllerFlow.takeLog("endpoints:executeEndpoint:Specialendpoint found");
             return;
         }
     }  ControllerFlow.takeLog("endpoints:executeEndpoint:ExecuteEndpoint");
        if(endpointrequestHandlers.containsKey(data.endpoint)){
            ControllerFlow.takeLog("endpoints:executeEndpoint:endpoint found");
            endpointrequestHandlers.get(data.endpoint).execute(data.method,data,out);
        }
    }
}

