package org.server;

import java.io.IOException;
import org.server.PrintWriterwithStream;

public class starterPage implements specialEndpoints{
    @Override
    public boolean checkandrun(String endpoint, inputData data, PrintWriterwithStream out) throws IOException {
        ControllerFlow.takeLog("startPage:checkandrun");
       IO.println("end->"+endpoint+"<-end");
        if(endpoint==null || endpoint.isEmpty() || endpoint.equals("index.html")){
        respondFile.respond("index.html",out,data.headers.get("Range"));
        return true;
     }else{
         return  false;
     }
    }
}
