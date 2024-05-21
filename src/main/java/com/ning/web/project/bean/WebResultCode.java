package com.ning.web.project.bean;


import com.ning.web.jotato.base.model.IResultCode;

public enum WebResultCode implements IResultCode {

    TOKEN_STATUS_INVALID("U0010", "请先登录"),
    TOKEN_STATUS_EXPIRED("U0011", "会话已过期,请重新登录"),
    USER_GEETEST_VALIDATE_FAIL("U0012", "未知账户，操作不允许"),
    ;
	private String code;
    private String msg;

    // 构造方法
    WebResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	@Override
    public String code() {
		return code;
	}

	@Override
    public String message() {
		return msg;
	}

	public boolean isSuccess() {
		return this == SUCCESS;
	}
}
