package com.ning.web.jotato.base.exception;

import com.ning.web.jotato.base.model.IResult;
import lombok.Getter;

import java.util.Objects;

public class CommonException extends RuntimeException {
	private static final long serialVersionUID = 8838429880037766070L;
	@Getter
	private IResult result;
	private String code;

	public CommonException(IResult result) {
		this(result, result.getMessage());
	}

	public CommonException(IResult result, String message) {
		super(message);
		this.result = result;
	}

	public CommonException(IResult result, Throwable cause) {
		this(result, result.getMessage(), cause);
	}

	public CommonException(IResult result, String message, Throwable cause) {
		super(message, cause);
		this.result = result;
	}

	public CommonException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		if (Objects.nonNull(this.code)) {
			return this.code;
		} else {
			return Objects.nonNull(this.result) ? this.result.getCode() : null;
		}
	}
}
