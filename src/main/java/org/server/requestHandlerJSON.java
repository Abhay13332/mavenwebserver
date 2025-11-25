package org.server;

import java.io.IOException;

@FunctionalInterface
public interface requestHandlerJSON<T,U>{
    public void run(reqObjson req, resObjson res) throws IOException;
}
