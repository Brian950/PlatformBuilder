package com.logic.logging;

public class LoggingService {

    public LoggingService(){

    }

    public void logMessage(Context context, Dispatcher dispatcher){
        System.out.println("Log message: "+context.getMessage());
    }
}
