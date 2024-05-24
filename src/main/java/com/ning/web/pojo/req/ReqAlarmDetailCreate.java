package com.ning.web.pojo.req;

import com.ning.web.jotato.core.request.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* AlarmDetail创建参数
* @author ning
* @since 2024-05-25 01:22:42
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ReqAlarmDetailCreate extends BaseReq {


    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 告警主键id
     */
    private Long alarmId;

    /**
     * 操作人
     */
    private Long userId;

    /**
     * 操作人告警时的ip地址
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



}
