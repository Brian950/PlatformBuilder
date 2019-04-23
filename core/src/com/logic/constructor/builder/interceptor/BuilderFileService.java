package com.logic.constructor.builder.interceptor;

public class BuilderFileService {

    public BuilderFileService(){}

    public void builderFileService(BuilderFileContext context, BuilderDispatcher dispatcher){
        BuilderFileInterceptor builderFileInterceptor = ConcreteBuilderInterceptor.getInstance();
        dispatcher.register(builderFileInterceptor);
        dispatcher.preRequest(context);
    }

}
