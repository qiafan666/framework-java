package com.ning.web.jotato.common.function;

@FunctionalInterface
public interface ThrowExceptionHandler{
    /**
     * *抛出异常信息
     * *@parammessage异常信息
     * *@returnvoid
     *
     * **/
    void throwMessage(String message);
}
