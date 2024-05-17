package com.ning.web.jotato.base.model.result;

import com.ning.web.jotato.base.model.IResult;
import com.ning.web.jotato.base.model.IResultCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class BaseResult extends BaseBean {
    private static final long serialVersionUID = -6440697160058238736L;
    private boolean success;
    private String code;
    private String msg;

    public BaseResult() {
        this.success = true;
        this.code = IResult.SUCCESS.getCode();
        this.msg = IResult.SUCCESS.getMessage();
    }

    public BaseResult(Boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(IResult result) {
        this.init(result);
    }

    protected final void init(IResult result) {
        this.success = IResult.SUCCESS == result;
        this.code = result.getCode();
        this.msg = result.getMessage();
    }

    public final void init(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public <T extends BaseResult> T setIResult(IResult resultCode) {
        this.init(resultCode);
        return (T) this;
    }

    public <T extends BaseResult> T override(BaseResult resultBase) {
        this.init(resultBase);
        return (T) this;
    }

    protected final void init(BaseResult otherResult) {
        this.success = otherResult.isSuccess();
        this.code = otherResult.getCode();
        this.msg = otherResult.getMsg();
    }

    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        result.setCode(IResult.SUCCESS.getCode());
        result.setMsg(IResult.SUCCESS.getMessage());
        return result;
    }

    public static BaseResult fail(String code, String msg) {
        return new BaseResult(IResult.SUCCESS.getCode().equals(code), code, msg);
    }
    
    public static BaseResult build(boolean success, String code, String msg) {
        return new BaseResult(success, code, msg);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
