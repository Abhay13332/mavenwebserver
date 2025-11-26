package org.server;

import javax.xml.crypto.Data;
import java.io.IOException;
import org.server.PrintWriterwithStream;

public interface specialEndpoints{
    public boolean checkandrun(String endpoint, inputData data, PrintWriterwithStream out) throws IOException;
}

