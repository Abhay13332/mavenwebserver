package org.server;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);

        OutputStream out = socket.getOutputStream();
        out.write(("POST /login?qb=add HTTP/1.1\\r\\n\n" +
                "Host: example.com\\r\\n\n" +
                "Content-Length: 13\\r\\n\n" +
                "\\r\\n" +
                "username=abhay").getBytes());
        out.flush();

        socket.close();
    }
}