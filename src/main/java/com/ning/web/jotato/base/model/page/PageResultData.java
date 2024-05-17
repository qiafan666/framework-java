package com.ning.web.jotato.base.model.page;

import com.ning.web.jotato.base.model.IResult;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PageResultData<T> extends BaseResultData<PageResult<T>> {
    private static final long serialVersionUID = -4856518552962725119L;

    public PageResultData() {
    }

    public PageResultData(PageResult<T> data) {
        super.setData(data);
    }

    public PageResultData(Boolean success, String code, String msg, PageResult<T> data) {
        super(success, code, msg, data);
    }

    public PageResultData<T> setIResult(IResult resultCode) {
        super.setIResult(resultCode);
        return this;
    }

    public PageResultData<T> override(BaseResult resultBase) {
        super.override(resultBase);
        return this;
    }

    public static <T> PageResultData<T> success(PageResult<T> data) {
        return new PageResultData<>(data);
    }

    public static <T> PageResultData<T> fail(String code, String msg, PageResult<T> data) {
        return new PageResultData<>(false, code, msg, data);
    }

    public static <T> PageResultData<T> build(Boolean success, String code, String msg, PageResult<T> data) {
        return new PageResultData<>(success, code, msg, data);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
