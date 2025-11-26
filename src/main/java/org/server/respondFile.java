package org.server;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public  class respondFile extends response{




     protected void respond(interMediateData Data, PrintWriterwithStream output) throws IOException {
         respond("index.html",output,Data.headers.get("Range"));
     }
    public static String ContentTypeString(String ext){
       return switch (ext) {
            case "html", "htm" -> ("Content-Type: text/html");
            case "css" -> ("Content-Type: text/css");
            case "js" -> ("Content-Type: application/javascript");
            case "json" -> ("Content-Type: application/json");
            case "xml" -> ("Content-Type: application/xml");
            case "txt" -> ("Content-Type: text/plain");
            case "pdf" -> ("Content-Type: application/pdf");
            case "png" -> ("Content-Type: image/png");
            case "jpg", "jpeg" -> ("Content-Type: image/jpeg");
            case "gif" -> ("Content-Type: image/gif");
            case "svg" -> ("Content-Type: image/svg+xml");
            case "webp" -> ("Content-Type: image/webp");
            case "ico" -> ("Content-Type: image/x-icon");
            case "bmp" -> ("Content-Type: image/bmp");
            case "tiff", "tif" -> ("Content-Type: image/tiff");
            case "woff" -> ("Content-Type: font/woff");
            case "woff2" -> ("Content-Type: font/woff2");
            case "ttf" -> ("Content-Type: font/ttf");
            case "otf" -> ("Content-Type: font/otf");
            case "eot" -> ("Content-Type: application/vnd.ms-fontobject");
            case "mp3" -> ("Content-Type: audio/mpeg");
            case "wav" -> ("Content-Type: audio/wav");
            case "ogg" -> ("Content-Type: audio/ogg");
            case "m4a" -> ("Content-Type: audio/mp4");
            case "mp4" -> ("Content-Type: video/mp4");
            case "webm" -> ("Content-Type: video/webm");
            case "avi" -> ("Content-Type: video/x-msvideo");
            case "mov" -> ("Content-Type: video/quicktime");
            case "mkv" -> ("Content-Type: video/x-matroska");
            case "zip" -> ("Content-Type: application/zip");
            case "rar" -> ("Content-Type: application/x-rar-compressed");
            case "tar" -> ("Content-Type: application/x-tar");
            case "gz" -> ("Content-Type: application/gzip");
            case "7z" -> ("Content-Type: application/x-7z-compressed");
            case "doc" -> ("Content-Type: application/msword");
            case "docx" -> ("Content-Type: application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "xls" -> ("Content-Type: application/vnd.ms-excel");
            case "xlsx" -> ("Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            case "ppt" -> ("Content-Type: application/vnd.ms-powerpoint");
            case "pptx" -> ("Content-Type: application/vnd.openxmlformats-officedocument.presentationml.presentation");

            // Default
            default -> ("Content-Type: application/octet-stream");
        };
    }
    public static void respond(String subpath,PrintWriterwithStream output,String range)  throws IOException {
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


        if(range!=null){
            FileChannel fileInput = FileChannel.open(confile.toPath(), StandardOpenOption.READ);
            int startByte=Integer.parseInt(range.split("-")[0]);
            int endByte=range.split("-").length>1?Integer.parseInt(range.split("-")[1]):Integer.MAX_VALUE;
            int counttowrite=Math.min(endByte-startByte,conlength-startByte);
            output.println("DATE: "+ responedate);
            output.println(ContentTypeString(extType));
            output.println("Content-Length: "+ conlength);
            output.println("Content-Disposition: attachment; filename=\""+confile.getName()+"\"");
            output.println("Content-Range: bytes "+range.strip()+"/"+conlength);
            output.println("Connection: close");
            output.println();

        }else{

            output.println("HTTP/1.1 200 OK");
            output.println("DATE: "+ responedate);
            output.println(ContentTypeString(extType));
            output.println("Content-Length: "+ conlength);
            output.println("Connection: close");
            output.println();
            Files.copy(confile.toPath(), output.outputStream);

        }

//
//         BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
//         String line;
//         while ((line = in.readLine()) != null) {
//             output.println(line);
//         }



     }
 }
