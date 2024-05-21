package com.ning.web.pojo.req;

import com.ning.web.jotato.core.request.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReqAppUpdate extends BaseReq {

    private Long appId;
}
