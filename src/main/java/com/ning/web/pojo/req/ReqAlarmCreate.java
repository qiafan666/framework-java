package com.ning.web.pojo.req;

import com.ning.web.jotato.core.request.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* Alarm创建参数
* @author ning
* @since 2024-05-25 23:28:56
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ReqAlarmCreate extends BaseReq {


    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 操作人
     */
    private Long userId;

    /**
     * 操作人告警时的ip
     */
    private String ip;

    /**
     * 应用主键id
     */
    private Long appId;

    /**
     * 策略主键id
     */
    private Long strategyId;

    /**
     * 行为主键id
     */
    private Long behaviorId;

    /**
     * 可信凭证
     */
    private String trustedCred;



}
