package com.ning.web.jotato.core.validator;


import com.ning.web.jotato.core.validator.enums.ValidatorType;
import com.ning.web.jotato.core.validator.valids.*;

public abstract class ValidatorFactory {
    public ValidatorFactory() {
    }

    public static Validator getValidator(String parameterType) {
        ValidatorType vt = ValidatorType.of(parameterType);
        if (vt != null) {
            switch (vt) {
                case STRING:
                    return new StringValidator();
                case LONG:
                    return new NumberValidator();
                case BOOLEAN:
                    return new BooleanValidator();
                case DOUBLE:
                    return new DecimalValidator();
                case DATE:
                    return new DateValidator();
                case DATETIME:
                    return new DateTimeValidator();
                case JSON:
                case JSONOBJECT:
                    return new JsonObjectValidator();
                case JSONARRAY:
                    return new JsonArrayValidator();
                default:
                     return null;
            }
        }

        return null;
    }
}
