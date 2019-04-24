package com.logic.constructor.builder.interceptor;


import java.util.ArrayList;

public class BuilderDispatcher implements BuilderFileInterceptor{

    private ArrayList<BuilderFileInterceptor> interceptors;

    public BuilderDispatcher(){
        interceptors = new ArrayList<BuilderFileInterceptor>();
    }

    @Override
    public void preRequest(BuilderFileContext context) {
        BuilderFileInterceptor i = interceptors.get(interceptors.size()-1);
        i.preRequest(context);
    }

    @Override
    public void postRequest(BuilderFileContext context) {
        BuilderFileInterceptor i = interceptors.get(interceptors.size()-1);
        i.postRequest(context);
    }

    public void register(BuilderFileInterceptor interceptor){
        interceptors.add(interceptor);
    }

    public void remove(BuilderFileInterceptor interceptor){
        interceptors.remove(interceptor);
    }
}
