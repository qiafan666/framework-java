package com.ning.web.base.bean;

import lombok.Data;

/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
@Data
public class ApiException extends RuntimeException {
    private IResultCode errorCode;

    public ApiException(IResultCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IResultCode getErrorCode() {
        return errorCode;
    }
}
