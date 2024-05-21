package com.ning.web.pojo.req;

import com.ning.web.jotato.core.request.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReqAppCreate extends BaseReq {
    /**
     * 应用名称
     */
    private String appName;
}
