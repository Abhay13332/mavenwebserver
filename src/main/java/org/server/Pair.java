package org.server;

public class Pair<T,U>{
    reqObj<T> first;
    resObj<U> second;
    public Pair(reqObj<T> first, resObj<U> second){
        this.first=first;
        this.second=second;

    }

}
