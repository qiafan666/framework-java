package com.ning.web.jotato.common.function;


import com.ning.web.jotato.common.exception.RestException;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    /**
     * *参数为true或false时，分别进行不同的操作
     *
     * @param str
     * @return com.example.demo.func.BranchHandle
     **/
    public PresentOrElseHandler<?> isBlankOrNoBlank(String str) {
        return (consumer, runnable) -> {
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }


    /**
     * *如果参数为true抛出异常
     * *@param b
     * *@return com.example.demo.func.ThrowExceptionFunction
     * *
     */
    public  ThrowExceptionHandler isTure(boolean b) {
        return (errorMessage) -> {
            if (b) {
                throw new RestException(errorMessage);
            }
        };
    }


    /**
     * *参数为true或false时，分别进行不同的操作
     * *@paramb
     * *@return com.example.demo.func.BranchHandle
     **/
    public  BranchHandle isTureOrFalse(boolean b) {
        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }
}
