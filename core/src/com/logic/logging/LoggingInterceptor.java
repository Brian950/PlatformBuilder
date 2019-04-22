package com.logic.logging;

import com.logic.logging.LogContext;

public interface LoggingInterceptor {

    void preRequest(LogContext c);
    void postRequest(LogContext c);

}
