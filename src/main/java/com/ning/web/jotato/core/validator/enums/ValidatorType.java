package com.ning.web.jotato.core.validator.enums;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ValidatorType {
    STRING,
    NUMBER,
    BOOLEAN,
    DOUBLE,
    DATE,
    DATETIME,
    JSON,
    JSONOBJECT,
    JSONARRAY;

    private static final Logger log = LoggerFactory.getLogger(ValidatorType.class);

    private ValidatorType() {
    }

    public static ValidatorType of(String parameterType) {
        try {
            return valueOf(parameterType.toUpperCase());
        } catch (Exception var2) {
            Exception e = var2;
            log.warn("Parameter type:[{}] error，Exception：", parameterType, e);
            return null;
        }
    }
}
