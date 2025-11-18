package org.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class inputData {
    HashMap<String,String> headers;
    requestType method;
     String endpoint;
     String content;
     HashMap<String,String> queries;

  public   inputData(BufferedReader in) throws IOException {
        headers=new HashMap<>();
        queries=new HashMap<>();
        headersparse(in);
    }
    private void endpointparse(String text){
      String stripped=text.strip();

       if(text.startsWith("GET")){
           method=requestType.GET;
       }else if(text.startsWith("POST")){
           method=requestType.POST;
       }else if(text.startsWith("PUT")){
           method=requestType.PUT;
       }else if(text.startsWith("DELETE")){
           method=requestType.DELETE;
       }
       StringBuilder builder=new StringBuilder();
       int spacest=0;
       for(int i=0;i<stripped.length();i++){
           if(spacest==1){
               if(stripped.charAt(i)!=' '){

               builder.append(text.charAt(i));
               }else{
                   spacest=0;
               }
           }else{

           if(text.charAt(i)==' '){
               spacest=1;
           }
           }
       }
       queryParser(builder);

    }

    private void queryParser(StringBuilder text) {


        StringBuilder endpoint = new StringBuilder();
       int idx=1;
      while(idx<text.length() && text.charAt(idx)!='?'){
          endpoint.append(text.charAt(idx++));
      }

      this.endpoint = endpoint.toString().equals(" ") ?"/":endpoint.toString();
      if(idx==text.length()){
          return;
      }
      idx++;
      while(idx<text.length()){
          StringBuilder querykey=new StringBuilder();
          StringBuilder queryvalue=new StringBuilder();
          while(idx<text.length() && text.charAt(idx)!='='){
             querykey.append(text.charAt(idx++));
          }
          if(idx==text.length()){
             return;
          }
          idx++;
          while(idx<text.length() && text.charAt(idx)!='&'){

             queryvalue.append(text.charAt(idx++));
          }

          idx++;
          queries.put(querykey.toString(),queryvalue.toString());
      }

    }

    private void methodparse(String method){}
    private void headersparse(BufferedReader in) throws IOException {
        String text=in.readLine();
        if(text==null){
            return;
        }
        ControllerFlow.takeLog(text,true);
        endpointparse(text);
        while(!text.isEmpty()){
            text=in.readLine();
             addHeader(text);
             ControllerFlow.takeLog(text,true);


        }
        if(headers.containsKey("Content-Length")){
            ControllerFlow.takeLog(headers.get("Content-Length"),true);
            char[] buffer=new char[Integer.parseInt(headers.get("Content-Length"))];
            int read = in.read(buffer);


            content=(new String(buffer,0,read)).strip();
             ControllerFlow.takeLog(content,true);
        }


    }

    private void addHeader(String text) {
        StringBuilder name=new StringBuilder();
        int idx=0;
        while(idx<text.length() && text.charAt(idx)!=':'){
            name.append(text.charAt(idx++));

        }

        idx++;
        if(idx>=text.length()){
            return;
        }
        String value=text.substring(idx).strip();
        headers.put(name.toString(),value);

    }

    public String toString(){
        StringBuilder out=new StringBuilder();
        if(!headers.isEmpty()){

        out.append(" headers:\n");
        for(String key:headers.keySet()){
            out.append("    ").append(key).append("->").append(headers.get(key)).append('\n');
        }
        }
        out.append("endpoint:").append(endpoint).append('\n');
        out.append("method:").append(method).append('\n');
        if(!queries.isEmpty()) {
            out.append("queries:\n");
            for (String key : queries.keySet()) {
                out.append("    ").append(key).append("->").append(queries.get(key)).append('\n');
            }
        }
        out.append("content:").append(content).append('\n');
        return out.toString().strip();
    }
}
