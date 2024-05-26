package com.ning.web.jotato.core.validator.field;

import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.annotion.FieldValidation;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class FieldValidator {

    public static void validate(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldValidation.class)) {
                FieldValidation annotation = field.getAnnotation(FieldValidation.class);
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(obj);

                    // 公共校验逻辑
                    if (annotation.required() && (value == null || value.toString().trim().isEmpty())) {
                        throw new RestException("SYS013", I18NUtil.getMessage("SYS013", new Object[]{field.getName()}));
                    }

                    // 校验逻辑
                    if (value == null || value.toString().trim().isEmpty()) {
                        if (!annotation.defaultValue().isEmpty()) {
                            try {
                                field.set(obj, annotation.defaultValue());
                            } catch (IllegalAccessException e) {
                                throw new RestException("SYS018", I18NUtil.getMessage("SYS018", new Object[]{field.getName()}));
                            }
                        }
                    }

                    if (field.getType() == String.class) {
                        validateString(field, (String) value, annotation);
                    }else if (field.getType() == Integer.class || field.getType() == Long.class) {
                        validateNumber(field, (Number) value, annotation);
                    }else {
                        // 其他类型的校验可以根据需要扩展，目前先报错
                        throw new RestException("SYS014", I18NUtil.getMessage("SYS014", new Object[]{field.getName()}));
                    }

                } catch (IllegalAccessException e) {
                    throw new RestException("SYS018", I18NUtil.getMessage("SYS018", new Object[]{field.getName()}));
                }


            }
        }
    }

    private static void validateString(Field field, String value, FieldValidation annotation) {
        if (annotation.length() != Long.MAX_VALUE && value.length() > annotation.length()) {
            throw new RestException("SYS015", I18NUtil.getMessage("SYS015", new Object[]{field.getName()}));
        }

        if (!annotation.regex().isEmpty() && !Pattern.matches(annotation.regex(), value)) {
            throw new RestException("SYS019", I18NUtil.getMessage("SYS019", new Object[]{field.getName(),value,annotation.regex()}));
        }
    }

    private static void validateNumber(Field field, Number value, FieldValidation annotation) {
        if (value.longValue() <= 0 && annotation.required()) {
            throw new RestException("SYS013", I18NUtil.getMessage("SYS013", new Object[]{field.getName()}));
        }

        long numValue = value.longValue();
        if (numValue < annotation.min()) {
            throw new RestException("SYS016", I18NUtil.getMessage("SYS016", new Object[]{field.getName(),value}));
        }
        if (numValue > annotation.max()) {
            throw new RestException("SYS017", I18NUtil.getMessage("SYS017", new Object[]{field.getName(),value}));
        }
    }
}