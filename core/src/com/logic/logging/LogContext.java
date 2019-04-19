package com.logic.logging;

public class LogContext {

    private String logMessage;
    private boolean logStatus;

    public LogContext(String logMessage, boolean logStatus){
        this.logMessage = logMessage;
        this.logStatus = logStatus;
    }

    public String getMessage(){
        return logMessage;
    }

    public boolean loggingEnabled(){
        return logStatus;
    }
}
