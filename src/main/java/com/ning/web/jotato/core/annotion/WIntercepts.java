package com.ning.web.jotato.core.annotion;


import com.ning.web.jotato.core.web.interceptor.custom.WInterceptor;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WIntercepts {
    Class<? extends WInterceptor>[] before() default {};

    Class<? extends WInterceptor>[] after() default {};
}