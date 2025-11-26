package org.server;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.PrintWriter;

public class PrintWriterwithStream extends PrintWriter {
    public OutputStream outputStream;
    public PrintWriterwithStream(OutputStream out) {
        super(out);
        outputStream =out;
    }

    public PrintWriterwithStream(BufferedWriter bufferedWriter, boolean b) {
        super(bufferedWriter,b);
    }

    public PrintWriterwithStream(BufferedWriter bufferedWriter, boolean b, OutputStream clos) {
        super(bufferedWriter,b);
        outputStream =clos;
    }
}

