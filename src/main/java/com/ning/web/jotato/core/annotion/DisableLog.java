package com.ning.web.jotato.core.annotion;

import java.lang.annotation.*;

/**
 * 关闭不打印日志的接口
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableLog {
}

