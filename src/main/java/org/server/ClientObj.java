package org.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientObj{
    BufferedReader in;
    PrintWriter out;
    Socket client;
    ClientObj(BufferedReader in,PrintWriter out,Socket client){

        this.in=in;
        this.out=out;
        this.client=client;
    }
}
