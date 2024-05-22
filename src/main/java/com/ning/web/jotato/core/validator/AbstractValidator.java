package com.ning.web.jotato.core.validator;


import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.core.validator.rule.ValidatorRule;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.STRING;

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

        //如果是必须的，并且没有这个key，则抛出异常
        if (rule.isMust() && this.key == null){
            throw new RestException("SYS012", I18NUtil.getMessage("SYS012", new Object[]{this.keyPath}));
        }
        //如果是不能为空的，并且没有这个value，则抛出异常
        if (!rule.isCanBeEmpty() && this.val.isEmpty()){
            throw new RestException("SYS013", I18NUtil.getMessage("SYS013", new Object[]{this.keyPath}));
        }
        //如果有长度限制，并且超过长度，则抛出异常
        if (this.val != null && (long)this.val.length() > rule.getLength()){
            throw new RestException("SYS017", I18NUtil.getMessage("SYS017", new Object[]{this.keyPath,rule.getLength()}));
        }

        //如果value为空，并且有默认值，则设置默认值
        if (this.key != null && rule.isCanBeEmpty() && Objects.equals(this.val, "") && StringUtils.isNotEmpty(rule.getDefVal())){
            input.put(this.key, rule.getDefVal().trim());
        }

        this.doValid();
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
