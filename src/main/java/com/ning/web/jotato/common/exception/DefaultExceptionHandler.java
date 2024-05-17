package com.ning.web.jotato.common.exception;

import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.common.i18n.I18NUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultExceptionHandler extends AbstractExceptionHandler {
    public DefaultExceptionHandler() {
    }


    @Override
    protected Object buildResp(String code, Throwable e) {
        String msg = I18NUtil.getMessage(code);
        this.logger.error(msg, e);
        return BaseResult.fail(code, msg);
    }

    @Override
    protected Object buildResp(String code, String msg) {
        return BaseResult.fail(code, msg);
    }
}
