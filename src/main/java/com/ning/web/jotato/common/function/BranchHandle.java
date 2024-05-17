package com.ning.web.jotato.common.function;

@FunctionalInterface
public interface BranchHandle{
    /**
     * *分支操作
     * *@param trueHandle为true时要进行的操作
     * *@param falseHandle为false时要进行的操作
     * *@return void
     * **/
    void trueOrFalseHandle(Runnable trueHandle,Runnable falseHandle);
}
