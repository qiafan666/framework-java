package com.ning.web.jotato.base.model.result;
import com.ning.web.jotato.base.model.IResult;


public class TokenResultData<T> extends BaseResultData<T> {
    private static final long serialVersionUID = 8041336886301494258L;
    private String token;

    public TokenResultData() {
    }

    public TokenResultData(T data, String token) {
        super(data);
        this.token = token;
    }

    public TokenResultData(Boolean success, String code, String msg, T data, String token) {
        super(success, code, msg, data);
        this.token = token;
    }

    public static <T> TokenResultData<T> success(T data, String token) {
        return new TokenResultData<>(data, token);
    }

    public static <T> TokenResultData<T> fail(IResult result) {
        return (TokenResultData)(new TokenResultData()).setIResult(result);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
