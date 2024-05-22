package com.ning.web.jotato.core.validator;


import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.rule.ValidatorRule;
import org.apache.commons.lang3.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator implements Validator {
    protected ValidatorRule rule;
    protected JSONObject input;
    protected String keyPath;
    protected String key;
    protected String val;
    protected boolean hasKey;

    public AbstractValidator() {
    }

    protected void init(ValidatorRule rule, JSONObject input) {
        this.rule = rule;
        this.input = input;
        this.keyPath = rule.getKeyPath();
        this.key = rule.getKey();
        this.hasKey = input.containsKey(this.key);
        this.val = input.getString(this.key) == null ? null : input.getString(this.key).trim();
    }

    public void valid(ValidatorRule rule, JSONObject input) {
        this.init(rule, input);
        if (!rule.isMust() || this.hasKey && this.val != null) {
            if (!rule.isCanBeEmpty() && "".equals(this.val)) {
                throw new RestException("SYS013", I18NUtil.getMessage("SYS013", new Object[]{this.keyPath}));
            } else if (this.val != null && (long)this.val.length() > rule.getLength()) {
                throw new RestException("SYS017", I18NUtil.getMessage("SYS017", new Object[]{this.keyPath,rule.getLength()}));
            } else {
                if (this.val != null) {
                    this.doValid();
                } else {
                    String defVal = rule.getDefVal();
                    if (StringUtils.isNotEmpty(defVal)) {
                        input.put(this.key, defVal.trim());
                    }
                }

            }
        } else {
            throw new RestException("SYS012", I18NUtil.getMessage("SYS012", new Object[]{this.keyPath}));
        }
    }

    protected void doValidPattern() {
        if (StringUtils.isNotEmpty(this.val) && StringUtils.isNotEmpty(this.rule.getPattern())) {
            Pattern pattern = Pattern.compile(this.rule.getPattern());
            Matcher matcher = pattern.matcher(this.val);
            if (!matcher.matches()) {
                throw new RestException("SYS018", I18NUtil.getMessage("SYS018", new Object[]{this.keyPath}));
            }
        }

    }

    protected abstract void doValid();
}
