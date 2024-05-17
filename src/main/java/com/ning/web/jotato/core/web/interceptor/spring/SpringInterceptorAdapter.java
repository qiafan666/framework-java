package com.ning.web.jotato.core.web.interceptor.spring;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpringInterceptorAdapter extends HandlerInterceptorAdapter {
    public SpringInterceptorAdapter() {
    }

    public List<String> includePatterns() {
        return new ArrayList();
    }

    public List<String> excludePatterns() {
        return new ArrayList();
    }
}

