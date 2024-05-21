package com.ning.web.jotato.core.annotion;


import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageValid {
    String[] value();
}
