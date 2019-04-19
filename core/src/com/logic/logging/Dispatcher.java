package com.logic.logging;

import com.logic.common.Interceptor;

import java.util.ArrayList;

public class Dispatcher implements Interceptor {

    private static Dispatcher dispatcher;
    private ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();


    public Dispatcher(){

    }

    @Override
    public void preRequest(Context c) {
        Interceptor i = interceptors.get(interceptors.size()-1);
    }

    @Override
    public void postRequest(Context c) {
        Interceptor i = interceptors.get(interceptors.size()-1);
    }

    public void register(Interceptor i){
        interceptors.add(i);
    }

    public void remove(Interceptor i){
        interceptors.remove(i);
    }

    public Dispatcher getInstance(){
        if(dispatcher == null){
            return new Dispatcher();
        }
        else
            return dispatcher;
    }
}
