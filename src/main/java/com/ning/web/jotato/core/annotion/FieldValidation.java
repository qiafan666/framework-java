package com.ning.web.jotato.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 实体类校验注解 //目前只支持string Interger Long 类型
// required: 是否必填
// defaultValue: 默认值  value为空时，使用默认值
// length: 长度
// min: 最小值
// max: 最大值
// regex: 正则表达式
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValidation {

    // 校验是否必填
    boolean required() default false;
    // 校验默认值
    String defaultValue() default "";
    // 校验长度
    long length() default Long.MAX_VALUE;
    // 校验最小值
    long min() default Long.MIN_VALUE;
    // 校验最大值
    long max() default Long.MAX_VALUE;
    // 校验正则表达式
    String regex() default "";
}