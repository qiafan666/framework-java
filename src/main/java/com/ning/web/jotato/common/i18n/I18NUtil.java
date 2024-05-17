package com.ning.web.jotato.common.i18n;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18NUtil {
    private static MessageSource ms;

    public I18NUtil() {
    }

    @Autowired(
            required = false
    )
    public void setMessageSource(MessageSource messageSource) {
        ms = messageSource;
    }

    public static MessageSource getMessageSource() {
        return ms;
    }

    public static String getMessage(String code) {
        return getMessage(code, (Object[])null);
    }

    public static String getMessageInLocal(String code, Locale locale) {
        return ms.getMessage(code, (Object[])null, "", locale);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public static String getMessageExt(String code, Object... args) {
        return getMessage(code, args, "");
    }

    public static String getMessageInLocal(String code, Object[] args, Locale locale) {
        return ms.getMessage(code, args, "", locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        return ms.getMessage(code, args, defaultMessage, getLocal());
    }

    public static Locale getLocal() {
        return LocaleContextHolder.getLocale();
    }
}
