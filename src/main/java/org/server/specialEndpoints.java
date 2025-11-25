package org.server;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

public interface specialEndpoints{
    public boolean checkandrun(String endpoint, inputData data, PrintWriter out) throws IOException;
}

