package com.ning.web.jotato.core.validator.valids;

import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.AbstractValidator;
import com.ning.web.jotato.core.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumberValidator extends AbstractValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(NumberValidator.class);

    public NumberValidator() {
    }

    @Override
    protected void doValid() {

        if (StringUtils.isNotEmpty(this.val)) {
            super.doValidPattern();

            try {
                long newVal = Long.parseLong(this.val);
                //如果number must unempty，并且value=0，则抛出异常
                if (this.rule.isMust() && !this.rule.isCanBeEmpty() && newVal == 0){
                    throw new RestException("SYS013", I18NUtil.getMessage("SYS013", new Object[]{this.keyPath}));
                }

                long max;
                if (StringUtils.isNotEmpty(this.rule.getMin())) {
                    max = Long.parseLong(this.rule.getMin());
                    if (newVal < max) {
                        throw new RestException("SYS016", I18NUtil.getMessage("SYS016", new Object[]{this.keyPath, max}));
                    }
                }

                if (StringUtils.isNotEmpty(this.rule.getMax())) {
                    max = Long.parseLong(this.rule.getMax());
                    if (newVal > max) {
                        throw new RestException("SYS017", I18NUtil.getMessage("SYS017", new Object[]{this.keyPath, max}));
                    }
                }
            } catch (NumberFormatException var5) {
                NumberFormatException e = var5;
                log.error("Validation key:{} fail, Exception:", this.keyPath, e);
                throw new RestException("SYS014", I18NUtil.getMessage("SYS014", new Object[]{this.keyPath}));
            }
        }

    }
}
