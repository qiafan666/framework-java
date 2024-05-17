package com.ning.web.jotato.project.bean;

import lombok.Data;

@Data
public class RequestResult {

    private int status;

    private String result;

    private String message;

    public RequestResult() {
    }

    public RequestResult(int statusCode, String result, String message) {
        this.status = statusCode;
        this.result = result;
        this.message = message;
    }

}
