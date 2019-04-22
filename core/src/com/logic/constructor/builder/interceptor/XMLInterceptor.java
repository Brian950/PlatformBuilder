package com.logic.constructor.builder.interceptor;

public interface XMLInterceptor {

        void preRequest(XMLContext context);
        void postRequest(XMLContext context);

}
