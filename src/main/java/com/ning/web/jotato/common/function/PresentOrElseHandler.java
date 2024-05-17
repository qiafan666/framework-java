package com.ning.web.jotato.common.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface PresentOrElseHandler<T extends Object>{
    /**
     * *值不为空时执行消费操作
     * *值为空时执行其他的操作
     * *@param action值不为空时，执行的消费操作
     * *@param emptyAction值为空时，执行的操作
     * *@return void **/
    void presentOrElseHandle(Consumer< ? super T> action, Runnable emptyAction);

}
