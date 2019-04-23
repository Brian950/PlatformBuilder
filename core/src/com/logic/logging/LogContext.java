package com.logic.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogContext {

    private String logMessage;
    private boolean logStatus;
    private String time = "";

    public LogContext(String logMessage, boolean logStatus){
        this.logMessage = logMessage;
        this.logStatus = logStatus;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        time = dateFormat.format(date);

    }

    public String getMessage(){
        return logMessage;
    }

    public String getTime(){return time;}

    public boolean loggingEnabled(){
        return logStatus;
    }
}
