package com.logic.logging;

import java.util.ArrayList;

public class LoggingDispatcher implements LoggingInterceptor {

    private static LoggingDispatcher dispatcher;
    private ArrayList<LoggingInterceptor> interceptors = new ArrayList<LoggingInterceptor>();


    public LoggingDispatcher(){

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

    public static LoggingDispatcher getInstance(){
        if(dispatcher == null){
            return new LoggingDispatcher();
        }
        else
            return dispatcher;
    }
}
