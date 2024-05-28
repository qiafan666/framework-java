package com.ning.web.pojo.resp;
import lombok.Data;

/**
* AlarmDetail列表返回结果
* @author ning
* @since 2024-05-28 21:20:47
*/
@Data
public class RespAlarmDetailList {


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
