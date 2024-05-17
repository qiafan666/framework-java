package com.ning.web.jotato.base.exception;

import com.ning.web.jotato.base.model.IResult;

public class ValidationException extends CommonException {
    private static final long serialVersionUID = -4212720177082824217L;

    public ValidationException(IResult result) {
        this(result, result.getMessage());
    }

    public ValidationException(IResult result, String message) {
        super(result, message);
    }

    public ValidationException(String message) {
        super(IResult.ILLEGAL_ARGUMENT, message);
    }

    public ValidationException(Throwable cause) {
        super(IResult.ILLEGAL_ARGUMENT, cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(IResult.ILLEGAL_ARGUMENT, message, cause);
    }
}
