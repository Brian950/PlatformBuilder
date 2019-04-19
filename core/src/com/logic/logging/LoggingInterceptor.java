package com.logic.logging;

import com.logic.common.Interceptor;

import java.util.ArrayList;

public class LoggingInterceptor implements Interceptor {

    private static LoggingInterceptor interceptor;
    private ArrayList<String> log;

    public LoggingInterceptor(){
    }

    @Override
    public void preRequest(Context c) {

    }

    @Override
    public void postRequest(Context c) {

    }

    public LoggingInterceptor getInstance(){
        if(interceptor == null){
            return new LoggingInterceptor();
        }
        else
            return interceptor;
    }
}
