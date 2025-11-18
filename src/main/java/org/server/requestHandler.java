package org.server;

import java.io.IOException;

public interface requestHandler<T,U>{
   Pair<T,U> run(reqObj<T> req,resObj<U> res) throws IOException;
}
