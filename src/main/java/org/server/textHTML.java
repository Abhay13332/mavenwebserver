package org.server;

import java.util.HashMap;

public class textHTML extends interMediateData{
    String body;
    public textHTML(HashMap<String,String> headers, String content) {
        super("text/html",headers);
        body=content;

    }
   public String getBody() {
        return body;
    }
   public void setBody(String body) {
        this.body=body;

    }
}
