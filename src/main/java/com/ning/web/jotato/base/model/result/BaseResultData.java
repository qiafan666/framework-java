package com.ning.web.jotato.base.model.result;

import com.ning.web.jotato.base.model.IResult;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseResultData<T> extends BaseResult {
    private static final long serialVersionUID = 3810185411604613988L;
    private T data;

    public BaseResultData() {
    }

    public BaseResultData(T data) {
        this.data = data;
    }

    public BaseResultData(Boolean success, String code, String msg, T data) {
        super(success, code, msg);
        this.data = data;
    }

    public static <T> BaseResultData<T> success(T data) {
        BaseResultData<T> result = new BaseResultData(data);
        result.setSuccess(true);
        result.setCode(IResult.SUCCESS.getCode());
        result.setMsg(IResult.SUCCESS.getMessage());
        return result;
    }

    public static <T> BaseResultData<T> failure(String code, String msg) {
        return new BaseResultData(false, code, msg, null);
    }

    public static <T> BaseResultData<T> failureWithData(String code, String msg, T data) {
        return new BaseResultData(false, code, msg, data);
    }

    public static <T> BaseResultData<T> build(Boolean success, String code, String msg, T data) {
        return new BaseResultData(success, code, msg, data);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
