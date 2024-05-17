package com.ning.web.jotato.common.exception;


import com.ning.web.jotato.common.i18n.I18NUtil;
import lombok.Getter;

@Getter
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public RestException() {
    }

    public RestException(String code) {
        this.code = code;
        this.msg = I18NUtil.getMessage(code);
    }

    public RestException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RestException of(String code, Object... args) {
        return new RestException(code, I18NUtil.getMessageExt(code, args));
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
