package org.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
class alreadyCreated extends RuntimeException {
    alreadyCreated(String method){
        super(method+":this method is added previously");
    }
}


public class endpoints {
    HashMap<String,endpoint > endpointrequestHandlers ;
    ArrayList<specialEndpoints> endpoints  ;
    endpoints(String mode){
        ControllerFlow.takeLog("endpoints:Constructor:mode:"+mode);
        endpointrequestHandlers = new HashMap<>();
        endpoints = new ArrayList<>();
        endpoints.add(new resources());
        if(mode.equals("webserver")){

        endpoints.add(new starterPage());
        }else if(mode.equals("Api")){

        }

    }

    void createEndpoint(String endpoint,endpoint endpointHandler){
        ControllerFlow.takeLog("endpoints:createEndpoint:"+endpoint);
        endpointrequestHandlers.put(endpoint,endpointHandler);


    }
    void executeEndpoint(inputData data, PrintWriter out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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

