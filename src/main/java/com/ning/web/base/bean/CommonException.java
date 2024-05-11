package com.ning.web.base.bean;


public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 8838429880037766070L;
	/**
	 * 应答码
	 */
	private IResultCode result;

	/**
	 * 构造方法
	 * 
	 * @param result
	 */
	public CommonException(IResultCode result) {
		this(result, result.message());
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param message
	 */
	public CommonException(IResultCode result, String message) {
		super(message);
		this.result = result;
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param cause
	 */
	public CommonException(IResultCode result, Throwable cause) {
		this(result, result.message(), cause);
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param message
	 * @param cause
	 */
	public CommonException(IResultCode result, String message, Throwable cause) {
		super(message, cause);
		this.result = result;
	}

	public void setResult(IResultCode result) {
		this.result = result;
	}

	public IResultCode getResult() {
		return result;
	}
}