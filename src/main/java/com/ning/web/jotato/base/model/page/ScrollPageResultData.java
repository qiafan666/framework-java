package com.ning.web.jotato.base.model.page;

import com.ning.web.jotato.base.model.IResult;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
public class ScrollPageResultData<T> extends BaseResultData<ScrollPageResult<T>> {
    private static final long serialVersionUID = -4544736886296779774L;

    public ScrollPageResultData() {
    }

    public ScrollPageResultData(ScrollPageResult<T> data) {
        super.setData(data);
    }

    public ScrollPageResultData(Boolean success, String code, String msg, ScrollPageResult<T> data) {
        super(success, code, msg, data);
    }

    public ScrollPageResultData<T> setIResult(IResult resultCode) {
        super.setIResult(resultCode);
        return this;
    }

    public ScrollPageResultData<T> override(BaseResult resultBase) {
        super.override(resultBase);
        return this;
    }

    public static <T> ScrollPageResultData<T> success(ScrollPageResult<T> data) {
        return new ScrollPageResultData<>(data);
    }

    public static <T> ScrollPageResultData<T> fail(String code, String msg, ScrollPageResult<T> data) {
        return new ScrollPageResultData<>(false, code, msg, data);
    }

    public static <T> ScrollPageResultData<T> build(Boolean success, String code, String msg, ScrollPageResult<T> data) {
        return new ScrollPageResultData(success, code, msg, data);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
