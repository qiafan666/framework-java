package com.ning.web.jotato.core.validator.valids;

import com.ning.web.jotato.core.validator.AbstractValidator;
import com.ning.web.jotato.core.validator.Validator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaozhao
 */
public class StringValidator extends AbstractValidator implements Validator {
    public StringValidator() {
    }

    @Override
    protected void doValid() {
        if (StringUtils.isNotEmpty(this.val)) {
            super.doValidPattern();
            this.input.put(this.key, this.val);
        }

    }
}
