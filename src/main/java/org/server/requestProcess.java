package org.server;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

public interface requestProcess{

    void execute(inputData data, PrintWriterwithStream out)throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
