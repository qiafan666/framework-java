package com.ning.web.pojo.resp;
import lombok.Data;

/**
* Alarm列表返回结果
* @author ning
* @since 2024-05-25 23:28:56
*/
@Data
public class RespAlarmList {


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
