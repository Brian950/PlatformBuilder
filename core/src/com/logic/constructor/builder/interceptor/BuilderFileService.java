package com.logic.constructor.builder.interceptor;

public class BuilderFileService {

    public BuilderFileService(){}

    public void runService(BuilderFileContext context, BuilderDispatcher dispatcher){
        BuilderFileInterceptor builderFileInterceptor = ConcreteBuilderInterceptor.getInstance();
        dispatcher.register(builderFileInterceptor);
        dispatcher.preRequest(context);
        dispatcher.postRequest(context);
        System.out.println("Data: "+ context.getdata());
    }

}
