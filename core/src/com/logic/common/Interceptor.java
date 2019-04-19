package com.logic.common;

import com.logic.logging.LogContext;

public interface Interceptor {

    void preRequest(LogContext c);
    void postRequest(LogContext c);

}
