package com.ning.web.aop;

import com.ning.web.base.bean.BizException;
import com.ning.web.base.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        log.error(e.getMessage());
        return CommonResult.failed(e.getMessage());
    }*/

    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public CommonResult exceptionHandle(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return CommonResult.failed();
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        log.error(e.getMessage());
        e.printStackTrace();
        return CommonResult.failed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public CommonResult handleValidException(BizException e) {
        log.error(e.getMessage());
        return CommonResult.failed(e.getResult());
    }
}
