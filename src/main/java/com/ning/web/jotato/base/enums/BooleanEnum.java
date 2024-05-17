package com.ning.web.jotato.base.enums;

public enum BooleanEnum {
    SUCCESS(true, 1),
    FAILURE(false, 0),
    TRUE(true, 1),
    FALSE(false, 0),
    T(true, 1),
    S(true, 1),
    F(false, 0),
    Y(true, 1),
    N(false, 0);

    private final boolean blnCode;
    private final Integer intCode;

    private BooleanEnum(boolean blnCode, int intCode) {
        this.blnCode = blnCode;
        this.intCode = intCode;
    }

    public boolean getBoolean() {
        return this.blnCode;
    }

    public Integer getInteger() {
        return this.intCode;
    }

    public static BooleanEnum getEnumByName(String name) {
        return name == null ? null : valueOf(name.toUpperCase());
    }
}
