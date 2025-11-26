package org.server;

import java.io.IOException;

public class resUtil{
    resObjson res;
    PrintWriterwithStream out;
   public resUtil(PrintWriterwithStream out, resObjson res){
        this.res=res;
        this.out=out;
    }
    public void sendstatus(int status){
        out.println(response.codesstart.get(status));
    }
    public void setDefault() throws IOException {
        response.setDefault(res.headers);
    }
    public void sendHeader(){
       IO.println(res.headers.keySet());
        for(String key : res.headers.keySet()){
            out.println(key + " : " + res.headers.get(key));
            ControllerFlow.takeLog(key + " : " + res.headers.get(key));

        }
        out.println();
    }
    public void setContentFile(String Ext){
        res.setHeaders("Content-Type",respondFile.ContentTypeString(Ext).substring("Content-Type: ".length()));
    }
    public void setContentType(String contentType){
        res.setHeaders("Content-Type", contentType);
    }
    public void setToDownload(String filename){
        filename=filename.strip();
        setContentFile(filename.substring(filename.lastIndexOf(".")+1));
        res.setHeaders("Content-Disposition","attachment; filename="+filename);
        res.setHeaders("Cache-Control","no-store, no-cache, must-revalidate");
        res.setHeaders("Pragma","no-cache");
        res.setHeaders("Expires","0");

    }





}
