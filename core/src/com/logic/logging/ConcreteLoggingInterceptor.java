package com.logic.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConcreteLoggingInterceptor implements LoggingInterceptor {

    private static ConcreteLoggingInterceptor interceptor;
    private ArrayList<String> log;
    private BufferedWriter writer;

    public ConcreteLoggingInterceptor() {

    }

    @Override
    public void preRequest(LogContext context) {
        try {
            if(new File("log.txt").exists()) {
                writer = new BufferedWriter(new FileWriter("log.txt", true));
                writer.append("[" + context.getTime() + "] " + context.getMessage() + "\n");
                writer.close();
            }
            else{
                writer = new BufferedWriter(new FileWriter("log.txt"));
                writer.write("[" + context.getTime() + "] " + context.getMessage() + "\n");
                writer.close();
            }
        } catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Override
    public void postRequest(LogContext context) {
        System.out.println("PostReq Log message: "+context.getMessage());
    }

    public static ConcreteLoggingInterceptor getInstance(){
        if(interceptor == null){
            return new ConcreteLoggingInterceptor();
        }
        else
            return interceptor;
    }
}
