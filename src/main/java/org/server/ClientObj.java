package org.server;

import java.io.BufferedReader;

import java.net.Socket;

public class ClientObj{
    BufferedReader in;

    PrintWriterwithStream out;
    Socket client;
    ClientObj(BufferedReader in,PrintWriterwithStream out,Socket client){

        this.in=in;
        this.out=out;
        this.client=client;
    }

}
