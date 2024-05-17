package com.ning.web.jotato.base.model;


import com.ning.web.jotato.base.exception.BizException;
import com.ning.web.jotato.base.exception.CommonException;
import com.ning.web.jotato.base.exception.ValidationException;

public interface IExceptionResult extends IResult {
    IExceptionResult SYSTEM_ERROR = new IExceptionResult() {
        public <E extends CommonException> E getException() {
            return this.getException(this.getMessage());
        }

        public <E extends CommonException> E getException(String message, String... args) {
            return (E) (args != null && args.length > 0 ? new BizException(this, String.format(message, args)) : new BizException(this, message));
        }

        public String getCode() {
            return IResult.SYSTEM_ERROR.getCode();
        }

        public String getMessage() {
            return IResult.SYSTEM_ERROR.getMessage();
        }
    };
    IExceptionResult ILLEGAL_ARGUMENT = new IExceptionResult() {
        public <E extends CommonException> E getException() {
            return this.getException(this.getMessage());
        }

        public <E extends CommonException> E getException(String message, String... args) {
            return (E) (args != null && args.length > 0 ? new ValidationException(this, String.format(message, args)) : new ValidationException(this, message));
        }

        public String getCode() {
            return IResult.ILLEGAL_ARGUMENT.getCode();
        }

        public String getMessage() {
            return IResult.ILLEGAL_ARGUMENT.getMessage();
        }
    };

    <E extends CommonException> E getException();

    <E extends CommonException> E getException(String var1, String... var2);
}
