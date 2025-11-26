package org.server;

import java.io.IOException;

@FunctionalInterface
public interface runnableResponse {
     void respond(PrintWriterwithStream out,resUtil resUtil) throws IOException;
}
