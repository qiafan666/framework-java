package com.ning.web.pojo.req;

import com.ning.web.jotato.core.request.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* AlarmDetail更新参数
* @author ning
* @since 2024-05-28 21:20:47
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ReqAlarmDetailUpdate extends BaseReq {


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
