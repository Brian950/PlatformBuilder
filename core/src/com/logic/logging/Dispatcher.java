package com.logic.logging;

import com.logic.common.Interceptor;

import java.util.ArrayList;

public class Dispatcher implements Interceptor {

    private static Dispatcher dispatcher;
    private ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();


    public Dispatcher(){

    }

    @Override
    public void preRequest(LogContext context) {
        Interceptor i = interceptors.get(interceptors.size()-1);
        if(context.loggingEnabled()){
            System.out.println("Dispatcher Pre Message: " + context.getMessage());
            i.preRequest(context);
        }
    }

    @Override
    public void postRequest(LogContext context) {
        Interceptor i = interceptors.get(interceptors.size()-1);
        if(context.loggingEnabled()){
            System.out.println("Dispatcher Post Message: " + context.getMessage());
            i.preRequest(context);
        }
    }

    public void register(Interceptor i){
        interceptors.add(i);
    }

    public void remove(Interceptor i){
        interceptors.remove(i);
    }

    public static Dispatcher getInstance(){
        if(dispatcher == null){
            return new Dispatcher();
        }
        else
            return dispatcher;
    }
}
