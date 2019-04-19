package com.logic.logging;

import com.logic.common.Interceptor;

public class LoggingService {

    public LoggingService(){

    }

    public void log(LogContext context, Dispatcher dispatcher){
        Interceptor logMessage = LoggingInterceptor.getInstance();
        dispatcher.register(logMessage);
        dispatcher.preRequest(context);
    }
}
