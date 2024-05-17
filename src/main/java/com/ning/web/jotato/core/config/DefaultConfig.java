package com.ning.web.jotato.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "default")
public class DefaultConfig {

    private String defaultPassword = "123456";

    private int tenantLicenseMonth = 120;

    private int companyRecycleDay = 30;

    private int userRecycleDay = 30;
    /**
     * 系统AES加密密钥
     */
    private String aesKey;

    /**
     * 登录密码连续错误次数
     */
    private long signInErrorTimes = 5;

    /**
     * 登录密码连续错误时间区间（单位分钟）
     */
    private long signInErrorMinutes = 15;

    /**
     * 登录强制锁时间（单位分钟）
     */
    private long signInLockedMinutes = 30;



    /**
     * 消息发送次数每小时限制次数
     */
    private int preHourLimitMsg = 10;
}
