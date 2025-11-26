package org.server;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public  class respondFile extends response{




     protected void respond(interMediateData Data, PrintWriterwithStream output) throws IOException {
         respond("index.html",output);
     }
    static void PrintContentType(PrintWriterwithStream out,String ext){
        switch (ext) {
            case "html", "htm" -> out.println("Content-Type: text/html");
            case "css" -> out.println("Content-Type: text/css");
            case "js" -> out.println("Content-Type: application/javascript");
            case "json" -> out.println("Content-Type: application/json");
            case "xml" -> out.println("Content-Type: application/xml");
            case "txt" -> out.println("Content-Type: text/plain");
            case "pdf" -> out.println("Content-Type: application/pdf");
            case "png" -> out.println("Content-Type: image/png");
            case "jpg", "jpeg" -> out.println("Content-Type: image/jpeg");
            case "gif" -> out.println("Content-Type: image/gif");
            case "svg" -> out.println("Content-Type: image/svg+xml");
            case "webp" -> out.println("Content-Type: image/webp");
            case "ico" -> out.println("Content-Type: image/x-icon");
            case "bmp" -> out.println("Content-Type: image/bmp");
            case "tiff", "tif" -> out.println("Content-Type: image/tiff");
            case "woff" -> out.println("Content-Type: font/woff");
            case "woff2" -> out.println("Content-Type: font/woff2");
            case "ttf" -> out.println("Content-Type: font/ttf");
            case "otf" -> out.println("Content-Type: font/otf");
            case "eot" -> out.println("Content-Type: application/vnd.ms-fontobject");
            case "mp3" -> out.println("Content-Type: audio/mpeg");
            case "wav" -> out.println("Content-Type: audio/wav");
            case "ogg" -> out.println("Content-Type: audio/ogg");
            case "m4a" -> out.println("Content-Type: audio/mp4");
            case "mp4" -> out.println("Content-Type: video/mp4");
            case "webm" -> out.println("Content-Type: video/webm");
            case "avi" -> out.println("Content-Type: video/x-msvideo");
            case "mov" -> out.println("Content-Type: video/quicktime");
            case "mkv" -> out.println("Content-Type: video/x-matroska");
            case "zip" -> out.println("Content-Type: application/zip");
            case "rar" -> out.println("Content-Type: application/x-rar-compressed");
            case "tar" -> out.println("Content-Type: application/x-tar");
            case "gz" -> out.println("Content-Type: application/gzip");
            case "7z" -> out.println("Content-Type: application/x-7z-compressed");
            case "doc" -> out.println("Content-Type: application/msword");
            case "docx" -> out.println("Content-Type: application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "xls" -> out.println("Content-Type: application/vnd.ms-excel");
            case "xlsx" -> out.println("Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            case "ppt" -> out.println("Content-Type: application/vnd.ms-powerpoint");
            case "pptx" -> out.println("Content-Type: application/vnd.openxmlformats-officedocument.presentationml.presentation");

            // Default
            default -> out.println("Content-Type: application/octet-stream");
        }
    }
    static void respond(String subpath,PrintWriterwithStream output)  throws IOException {
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

        Files.copy(confile.toPath(), output.outputStream);

//
//         BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
//         String line;
//         while ((line = in.readLine()) != null) {
//             output.println(line);
//         }



     }
 }
