package com.ning.web.jotato.base.exception;

import com.ning.web.jotato.base.model.IResult;

/**
 * 系统业务级异常
 * 
 * @author doaction
 *
 */
public class BizException extends CommonException {
    private static final long serialVersionUID = 6141146090401109882L;

    public BizException(IResult result) {
        this(result, result.getMessage());
    }

    public BizException(IResult result, String message) {
        super(result, message);
    }

    public BizException(IResult result, Throwable cause) {
        super(result, result.getMessage(), cause);
    }

    public BizException(IResult result, String message, Throwable cause) {
        super(result, message, cause);
    }

    public BizException(String code, String message) {
        super(code, message);
    }
}
