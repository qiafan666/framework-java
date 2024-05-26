package com.ning.web.jotato.core.validator.rule;


public class ValidatorRule {
    private String keyPath = "";
    private String key = "";
    private String type = "string";
    private boolean isMust = false;
    private boolean canBeEmpty = false;
    private String defVal = "";
    private long length = Long.MAX_VALUE;
    private String min = null;
    private String max = null;
    private String pattern = null;
    private String[] param = null;

    public ValidatorRule() {
    }

    public ValidatorRule(String ruleString) throws Exception {
        if (!this.parseResult(ruleString)) {
            throw new Exception();
        }
    }

    public static ValidatorRule[] createRuleList(String[] rules) {
        ValidatorRule[] f = new ValidatorRule[rules.length];

        for(int i = 0; i < rules.length; ++i) {
            f[i] = new ValidatorRule();
            if (!f[i].parseResult(rules[i])) {
                return null;
            }
        }

        return f;
    }

    public boolean parseResult(String ruleString) {
        String[] v = ruleString.split(":");
        if (v.length == 0) {
            return false;
        } else {
            this.setKeyPath(this.getVal(v, 0, v[0]));
            this.setKey(this.getVal(v, 0, v[0]));
            this.setType(this.getVal(v, 1, "string").toLowerCase());
            this.setMust(this.getVal(v, 2, "opt").equalsIgnoreCase("must"));
            this.setCanBeEmpty(this.getVal(v, 3, "empty").equalsIgnoreCase("empty"));
            this.setDefVal(this.getVal(v, 4, ""));
            if (this.getVal(v, 5, String.valueOf(Long.MAX_VALUE)).isEmpty()) {
                this.setLength(Long.MAX_VALUE);
            }else {
                this.setLength(Long.parseLong(this.getVal(v, 5, String.valueOf(Long.MAX_VALUE))));
            }
            this.setMin(this.getVal(v, 6, ""));
            this.setMax(this.getVal(v, 7, ""));
            this.setPattern(this.getVal(v, 8, ""));
            int mustLen = 9;
            if (v.length <= mustLen) {
                return true;
            } else {
                this.param = new String[v.length - mustLen];

                for(int i = mustLen; i < v.length; ++i) {
                    this.param[i - mustLen] = v[i];
                }

                return true;
            }
        }
    }

    private String getVal(String[] sta, int index, String def) {
        return sta.length <= index ? def : sta[index];
    }

    public String getParam(int num) {
        return this.param != null && this.param.length > num ? this.param[num] : null;
    }

    public String getKeyPath() {
        return this.keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMust() {
        return this.isMust;
    }

    public void setMust(boolean isMust) {
        this.isMust = isMust;
    }

    public boolean isCanBeEmpty() {
        return this.canBeEmpty;
    }

    public void setCanBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
    }

    public String getDefVal() {
        return this.defVal;
    }

    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMin() {
        return this.min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return this.max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String[] getParam() {
        return this.param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }
}
