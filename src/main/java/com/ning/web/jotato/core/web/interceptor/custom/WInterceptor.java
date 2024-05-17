package com.ning.web.jotato.core.web.interceptor.custom;


import com.ning.web.jotato.core.web.context.ActionContext;

public interface WInterceptor {
    void doIntercept(ActionContext actionContext);
}

