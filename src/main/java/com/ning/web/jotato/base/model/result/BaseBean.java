package com.ning.web.jotato.base.model.result;

import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.lang3.SerializationUtils;

public class BaseBean implements Serializable, Cloneable {
    private static final long serialVersionUID = -265741933689599525L;
    private String requestId = UUID.randomUUID().toString().replace("-", "");

    public BaseBean() {
    }

    public BaseBean(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object clone() {
        return SerializationUtils.clone(this);
    }
}
