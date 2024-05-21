package com.ning.web.jotato.core.validator;


import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.core.validator.rule.ValidatorRule;

public interface Validator {
    void valid(ValidatorRule rule, JSONObject input);
}
