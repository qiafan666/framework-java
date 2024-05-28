package com.ning.web.jotato.base.model.page;

import com.ning.web.jotato.base.model.IResult;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MyPageResultData<T> extends BaseResultData<MyPageResult<T>> {
    private static final long serialVersionUID = -4856518552962725119L;

    public MyPageResultData() {
    }

    public MyPageResultData(MyPageResult<T> data) {
        super.setData(data);
    }

    public MyPageResultData(Boolean success, String code, String msg, MyPageResult<T> data) {
        super(success, code, msg, data);
    }

    public MyPageResultData<T> setIResult(IResult resultCode) {
        super.setIResult(resultCode);
        return this;
    }

    public MyPageResultData<T> override(BaseResult resultBase) {
        super.override(resultBase);
        return this;
    }

    public static <T> MyPageResultData<T> success(MyPageResult<T> data) {
        return new MyPageResultData<>(data);
    }

    public static <T> MyPageResultData<T> fail(String code, String msg, MyPageResult<T> data) {
        return new MyPageResultData<>(false, code, msg, data);
    }

    public static <T> MyPageResultData<T> build(Boolean success, String code, String msg, MyPageResult<T> data) {
        return new MyPageResultData<>(success, code, msg, data);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
