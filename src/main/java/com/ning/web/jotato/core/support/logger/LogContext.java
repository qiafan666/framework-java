package com.ning.web.jotato.core.support.logger;

import org.slf4j.MDC;

public class LogContext {
    private static final ThreadLocal<String> LOG_CONTEXT = new ThreadLocal();
    public static final String REQUEST_ID = "requestId";

    public LogContext() {
    }

    public static void put(String requestId) {
        LOG_CONTEXT.set(requestId);
        MDC.put("requestId", requestId);
    }

    public static String get() {
        return (String)LOG_CONTEXT.get();
    }

    public static void destroy() {
        MDC.remove("requestId");
        LOG_CONTEXT.remove();
    }
}
