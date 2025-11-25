package org.server;

import java.io.IOException;
import java.io.PrintWriter;

public class starterPage implements specialEndpoints{
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
