package com.logic.constructor.builder.interceptor;

public interface BuilderFileInterceptor {

    void preRequest(BuilderFileContext context);
    void postRequest(BuilderFileContext context);

}
