package com.logic.logging;

import java.util.ArrayList;

public class Dispatcher implements LoggingInterceptor {

    private static Dispatcher dispatcher;
    private ArrayList<LoggingInterceptor> interceptors = new ArrayList<LoggingInterceptor>();


    public Dispatcher(){

    }

    @Override
    public void preRequest(LogContext context) {
        LoggingInterceptor i = interceptors.get(interceptors.size()-1);
        if(context.loggingEnabled()){
            System.out.println("Debug: " + context.getMessage());
            i.preRequest(context);
        }
    }

    @Override
    public void postRequest(LogContext context) {
        LoggingInterceptor i = interceptors.get(interceptors.size()-1);
        if(context.loggingEnabled()){
            System.out.println("Debug: " + context.getMessage());
            i.preRequest(context);
        }
    }

    public void register(LoggingInterceptor i){
        interceptors.add(i);
    }

    public void remove(LoggingInterceptor i){
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
