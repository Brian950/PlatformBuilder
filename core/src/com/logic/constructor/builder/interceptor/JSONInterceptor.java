package com.logic.constructor.builder.interceptor;

public interface JSONInterceptor {

    void preRequest(JSONContext context);
    void postRequest(JSONContext context);

}
