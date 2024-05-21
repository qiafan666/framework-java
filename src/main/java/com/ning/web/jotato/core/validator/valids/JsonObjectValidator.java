package com.ning.web.jotato.core.validator.valids;

import com.alibaba.fastjson.JSON;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.AbstractValidator;
import com.ning.web.jotato.core.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonObjectValidator extends AbstractValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(JsonObjectValidator.class);

    public JsonObjectValidator() {
    }

    @Override
    protected void doValid() {
        if (StringUtils.isNotEmpty(this.val)) {
            super.doValidPattern();

            try {
                JSON.parseObject(this.val);
            } catch (Exception var2) {
                Exception e = var2;
                log.error("Validation key:{} fail, Exception:", this.keyPath, e);
                throw new RestException("SYS018", I18NUtil.getMessage("SYS018", new Object[]{this.keyPath}));
            }
        }

    }
}
