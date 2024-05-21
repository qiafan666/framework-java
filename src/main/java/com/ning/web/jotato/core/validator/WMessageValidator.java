package com.ning.web.jotato.core.validator;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.common.annotion.AnnotationUtil;
import com.ning.web.jotato.core.annotion.MessageValid;
import com.ning.web.jotato.core.validator.rule.ValidatorRule;
import com.ning.web.jotato.core.web.context.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class WMessageValidator {
    private static final Logger logger = LoggerFactory.getLogger(WMessageValidator.class);

    public WMessageValidator() {
    }

    public void validInMeaasge(ActionContext context) {
        MessageValid valid = (MessageValid) AnnotationUtil.findMethodAnnotation(context.getInvokeMethod(), MessageValid.class);
        if (valid != null) {
            String[] rules = valid.value();
            if (rules != null && rules.length >= 1) {
                JSONObject input = new JSONObject();
                String inPacket = context.getInPacket();
                if (StringUtils.isNotEmpty(inPacket)) {
                    try {
                        input = JSON.parseObject(inPacket);
                    } catch (Exception var7) {
                        Exception e = var7;
                        logger.warn("Can not cast to JSONObject：", e);
                    }
                }

                this.validInMeaasge(input, rules);
                context.setInPacket(input.toJSONString());
            }
        }
    }

    public void validInMeaasge(JSONObject input, String[] rules) {
        ValidatorRule[] ruleList = ValidatorRule.createRuleList(rules);
        if (ruleList != null) {
            ValidatorRule[] var4 = ruleList;
            int var5 = ruleList.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                ValidatorRule rule = var4[var6];
                this.validInMessage(input, rule);
            }
        }

    }

    public void validInMessage(JSONObject input, ValidatorRule rule) {
        String path = rule.getKeyPath();
        if (!path.contains(".")) {
            this.valid(input, rule);
        } else {
            int index = path.indexOf(".");
            String parentPath = path.substring(0, index);
            String paramKey = path.substring(index + 1);
            rule.setKey(paramKey);
            Object obj = null;

            try {
                obj = JSON.parse(input.getString(parentPath));
            } catch (Exception var11) {
                Exception e = var11;
                logger.warn("JSON parse error", e);
            }

            JSONObject item;
            if (obj instanceof JSONObject) {
                item = (JSONObject)obj;
                this.valid(item, rule);
                input.put(parentPath, item);
            } else if (obj instanceof JSONArray) {
                JSONArray list = (JSONArray)obj;

                for(int i = 0; i < list.size(); ++i) {
                    JSONObject item1 = JSON.parseObject(list.getString(i));
                    this.valid(item1, rule);
                    list.set(i, item1);
                }

                input.put(parentPath, list);
            } else {
                item = new JSONObject();
                this.valid(item, rule);
            }
        }

    }

    private void valid(JSONObject input, ValidatorRule rule) {
        Validator validator = ValidatorFactory.getValidator(rule.getType());
        if (validator != null) {
            validator.valid(rule, input);
        }

    }
}
