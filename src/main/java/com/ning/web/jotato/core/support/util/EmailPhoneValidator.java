package com.ning.web.jotato.core.support.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPhoneValidator {

    // 邮箱正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    // 手机号码正则表达式
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String email = "example@test.com";
        String phoneNumber = "13800138000";

        System.out.println("Is valid email: " + isValidEmail(email));
        System.out.println("Is valid phone number: " + isValidPhoneNumber(phoneNumber));
    }
}
