package org.server;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

public interface specialEndpoints{
    public boolean checkandrun(String endpoint, inputData data, PrintWriter out) throws IOException;
}
class resources implements specialEndpoints{

    @Override
    public boolean checkandrun(String endpoint, inputData data, PrintWriter out) throws IOException {
        ControllerFlow.takeLog("resource:checkandrun");
        if(endpoint.startsWith("src")){
           respondFile.respond(endpoint,out);
           return true;

        }
           return  false;
    }

}
class starterPage implements specialEndpoints{
    @Override
    public boolean checkandrun(String endpoint, inputData data, PrintWriter out) throws IOException {
        ControllerFlow.takeLog("startPage:checkandrun");

        if(endpoint.equals("")|| endpoint.equals("index.html")){
        respondFile.respond("index.html",out);
        return true;
     }else{
         return  false;
     }
    }
}
