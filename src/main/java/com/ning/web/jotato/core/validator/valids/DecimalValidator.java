package com.ning.web.jotato.core.validator.valids;


import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.AbstractValidator;
import com.ning.web.jotato.core.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;

public class DecimalValidator extends AbstractValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(DecimalValidator.class);

    public DecimalValidator() {
    }

    @Override
    protected void doValid() {
        if (StringUtils.isNotEmpty(this.val)) {
            super.doValidPattern();

            try {
                BigDecimal inVal = new BigDecimal(this.val);
                BigDecimal max;
                if (StringUtils.isNotEmpty(this.rule.getMin())) {
                    max = new BigDecimal(this.rule.getMin());
                    if (inVal.compareTo(max) < 0) {
                        throw new RestException("SYS016", I18NUtil.getMessage("SYS016", new Object[]{this.keyPath, max}));
                    }
                }

                if (StringUtils.isNotEmpty(this.rule.getMax())) {
                    max = new BigDecimal(this.rule.getMax());
                    if (inVal.compareTo(max) > 0) {
                        throw new RestException("SYS017", I18NUtil.getMessage("SYS017", new Object[]{this.keyPath, max}));
                    }
                }
            } catch (NumberFormatException var3) {
                NumberFormatException e = var3;
                log.error("Validation key:{} fail, Exception:", this.keyPath, e);
                throw new RestException("SYS014", I18NUtil.getMessage("SYS014", new Object[]{this.keyPath}));
            }
        }

    }
}
