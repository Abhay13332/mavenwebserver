package org.server;
import java.nio.file.Path;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public  class respondFile extends response{




     protected void respond(interMediateData Data, PrintWriter output) throws IOException {
         respond("index.html",output);
     }
    static void PrintContentType(PrintWriter out,String ext){
         if(ext.equals("html")){
             out.println("Content-Type:text/html");
         }else if(ext.equals("js")){
             out.println("Content-Type:application/javascript");
         }else if(ext.equals("css")){
             out.println("Content-Type:text/css");
         } else if(ext.equals("png")){
             out.println("Content-Type:image/png");
         }else if(ext.equals("jpg")){
             out.println("Content-Type:image/jpeg");
         }else if(ext.equals("jpeg")){
             out.println("Content-Type:image/jpeg");
         }else if(ext.equals("gif")){
             out.println("Content-Type:image/gif");
         }else{
             out.println("Content-Type:text/html");
         }
    }
    static void respond(String subpath,PrintWriter output)  throws IOException {
        ControllerFlow.takeLog("RespondFile:respond");
        ControllerFlow.takeLog("RespondFile:respond:path"+subpath,true);
         SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MM yyyy | HH:mm:ss");
         sdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
         String responedate = sdf.format(new Date());
         String  path = String.valueOf(Path.of((System.getProperty("user.dir")),"/public/",subpath));
         IO.println(path);
         File confile = new File(path);
         int conlength=0;
         if (confile.exists() && confile.isFile()) {
             long size = confile.length();
             conlength = Math.toIntExact(size );

         } else {
             System.out.println("File does not exist or is not a file.");

         }

         int i=subpath.lastIndexOf('.');
         String extType=subpath.substring(i+1);

         output.println("HTTP/1.1 200 OK");
         output.println("DATE: "+ responedate);
         PrintContentType(output,extType);
         output.println("Content-Length: "+ conlength);
         output.println("Connection: close");
         output.println();



         BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
         String line;
         while ((line = in.readLine()) != null) {
             output.println(line);
         }



     }
 }
