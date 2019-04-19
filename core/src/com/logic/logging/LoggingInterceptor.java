package com.logic.logging;

import com.logic.common.Interceptor;

import java.util.ArrayList;

public class LoggingInterceptor implements Interceptor {

    private static LoggingInterceptor interceptor;
    private ArrayList<String> log;

    public LoggingInterceptor(){
    }

    @Override
    public void preRequest(LogContext context) {
        System.out.println("PreReq Log message: "+context.getMessage());
    }

    @Override
    public void postRequest(LogContext context) {
        System.out.println("PostReq Log message: "+context.getMessage());
    }

    public static LoggingInterceptor getInstance(){
        if(interceptor == null){
            return new LoggingInterceptor();
        }
        else
            return interceptor;
    }
}
