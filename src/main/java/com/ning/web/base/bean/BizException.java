package com.ning.web.base.bean;

/**
 * 系统业务级异常
 * 
 * @author doaction
 *
 */
public class BizException extends CommonException {

    private static final long serialVersionUID = 6141146090401109882L;

    /**
     * 构造方法
     * 
     * @param result
     */
    public BizException(IResultCode result) {
        this(result, result.message());
    }

    /**
     * 构造方法
     * 
     * @param result
     * @param message
     */
    public BizException(IResultCode result, String message) {
        super(result, message);
    }

    /**
     * 构造方法
     * 
     * @param result
     * @param cause
     */
    public BizException(IResultCode result, Throwable cause) {
        super(result, result.message(), cause);
    }

    /**
     * 构造方法
     * 
     * @param result
     * @param message
     * @param cause
     */
    public BizException(IResultCode result, String message, Throwable cause) {
        super(result, message, cause);
    }
}