package com.logic.logging;

// This is the application class in the Interceptor pattern
public class LoggingService {

    public LoggingService(){

    }

    public void log(LogContext context, Dispatcher dispatcher){
        LoggingInterceptor logMessage = ConcreteLoggingInterceptor.getInstance();
        dispatcher.register(logMessage);
        dispatcher.preRequest(context);
    }
}
