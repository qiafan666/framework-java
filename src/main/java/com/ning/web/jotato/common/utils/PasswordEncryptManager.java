package com.ning.web.jotato.common.utils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class PasswordEncryptManager {

    /**
     * 校验密码是否匹配
     *
     * @param paramPassword 参数密码
     * @param dbEncPassword 数据库存储密码
     * @return boolean
     */
    public boolean verifyPassword(@NonNull String paramPassword, @NonNull String dbEncPassword) {

        return StringUtils.hasText(paramPassword) && PwdUtil.verifyPBKDF2(paramPassword, dbEncPassword);
    }

    public String getMD5Password(String paramPassword) {
        return PwdUtil.getMD5(paramPassword);
    }


    /**
     * 对密码进行加密
     *
     * @param password 待加密密码
     * @return String
     */
    public String encrypt(@NonNull String password) {
        return PwdUtil.getPBKDF2Encryption(password);
    }
}
