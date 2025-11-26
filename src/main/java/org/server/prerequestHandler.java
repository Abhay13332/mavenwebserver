package org.server;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class prerequestHandler<T,U> implements requestProcess {


    Class<T> typereq;Class<U> typeresp;
    ArrayList<requestHandler<T,U>> handlers;
    response response ;

  public prerequestHandler(Class<T> typereq,Class<U> typeresp,response response ){
       this.typereq = typereq;
       this.typeresp = typeresp;
       this.response = response;
       handlers = new ArrayList<>();
    }
    public  prerequestHandler(response response ){
       this.response = response;
        handlers = new ArrayList<>();
    }
    public prerequestHandler(Class<T> typereq,response  response ){
       this.typereq = typereq;
       handlers = new ArrayList<>();
       this.response  =response;

    }



   public void addHandler(requestHandler<T,U> handler){
       IO.println("adding handler in Prerequesthandler");
       handlers.add(handler);

   }


    public void execute(inputData data, PrintWriterwithStream out) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IO.println("entering prerequestHandler");
       reqObj<T> req;resObj<U> resp;
       if(typereq!=null){
           req=new reqObj<>(data,typereq);
       }else{
           req=new reqObj<>(data);
       }
       if(typeresp!=null){
            resp=new resObj<>(typeresp);
       }else{
           resp=new resObj<>();
       }
       for(requestHandler<T,U> handler:handlers){

           Pair<T,U> pair=handler.run(req,resp);
           req=pair.first;
           resp=pair.second;
       }
        IO.println("exitting prerequestHandler");

       response.respond(resp.data,out);

   }

}




