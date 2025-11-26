package org.server;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class resources implements specialEndpoints{

    @Override
    public boolean checkandrun(String endpoint, inputData data, PrintWriterwithStream out) throws IOException {
        ControllerFlow.takeLog("resource:checkandrun");
        if( Files.exists(Path.of((System.getProperty("user.dir")),"/public/",endpoint)) ) {
           respondFile.respond(endpoint,out,data.headers.get("Range"));
           return true;

        }
           return  false;
    }

}
