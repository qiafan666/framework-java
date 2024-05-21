package com.ning.web.jotato.core.validator.valids;


import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.AbstractValidator;
import com.ning.web.jotato.core.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class DateValidator extends AbstractValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(DateValidator.class);
    private static final String DEF_FORMAT = "yyyy-MM-dd";

    public DateValidator() {
    }

    @Override
    protected void doValid() {
        if (StringUtils.isNotEmpty(this.val)) {
            String format = "yyyy-MM-dd";
            if (StringUtils.isNotEmpty(this.rule.getPattern())) {
                format = this.rule.getPattern();
            }

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateFormat.setLenient(false);
                dateFormat.parse(this.val);
                this.input.put(this.key, this.val);
            } catch (Exception var3) {
                Exception e = var3;
                log.error("Validation key:{} fail, Exception:", this.keyPath, e);
                throw new RestException("SYS018", I18NUtil.getMessage("SYS018", new Object[]{this.keyPath}));
            }
        }

    }
}
