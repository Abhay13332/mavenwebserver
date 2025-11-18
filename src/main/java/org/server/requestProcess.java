package org.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public interface requestProcess{

    void execute(inputData data, PrintWriter out)throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
