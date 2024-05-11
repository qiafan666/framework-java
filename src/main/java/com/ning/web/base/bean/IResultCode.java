package com.ning.web.base.bean;

public interface IResultCode {

    String code();

    String message();

    IResultCode SUCCESS = new IResultCode() {
        @Override
        public String code() {
            return "0000";
        }

        @Override
        public String message() {
            return "Success";
        }
    };

    IResultCode SYSTEM_ERROR = new IResultCode() {
        @Override
        public String code() {
            return "9999";
        }

        @Override
        public String message() {
            return "系统错误，请稍后重试！";
        }
    };

    IResultCode ILLEGAL_ARGUMENT = new IResultCode() {
        @Override
        public String code() {
            return "4999";
        }

        @Override
        public String message() {
            return "含有非法字符";
        }
    };
}
