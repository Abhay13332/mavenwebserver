package org.server;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

public class JSONprerequestHandler<T,U> implements requestProcess{

    requestHandlerJSON<T,U> reqHandler;


    public JSONprerequestHandler(requestHandlerJSON<T,U> reqHandler) {

        this.reqHandler=reqHandler;
    }




    @Override
    public void execute(inputData data, PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        reqHandler.run(new reqObjson(data),new resObjson(out));
    }
    
}
