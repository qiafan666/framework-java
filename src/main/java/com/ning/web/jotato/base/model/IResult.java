package com.ning.web.jotato.base.model;

public interface IResult {
    IResult SUCCESS = new IResult() {
        public String getCode() {
            return "0";
        }

        public String getMessage() {
            return "suc";
        }
    };
    IResult SYSTEM_ERROR = new IResult() {
        public String getCode() {
            return "-1";
        }

        public String getMessage() {
            return "服务处理出错，请稍等再试";
        }
    };
    IResult ILLEGAL_ARGUMENT = new IResult() {
        public String getCode() {
            return "-2";
        }

        public String getMessage() {
            return "非法的请求参数";
        }
    };

    String getCode();

    String getMessage();
}
