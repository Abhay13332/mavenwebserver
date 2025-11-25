package org.server;

import java.io.IOException;
import java.io.PrintWriter;

public class resources implements specialEndpoints{

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
