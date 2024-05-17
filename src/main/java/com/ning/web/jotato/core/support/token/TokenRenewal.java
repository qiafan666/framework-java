package com.ning.web.jotato.core.support.token;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户token续期
 */
@Component
@Slf4j
public class TokenRenewal {


    /**
     * 租户段token过期时间
     */
    private static final long TENANT_USER_EXT_TIME = 30;

    /**
     * 运营端token过期时间
     */
    private static final long OPERATION_USER_EXT_TIME =  30;


    /**
     * redis记录用户token,设置过期时间
     * @param userType 用户类型
     * @param token token
     */
    public void recordUser(String userType,Long userId,String token){


        //TODO 记录时间

}
    /**
     * 过期时间内用户有操作，自动续期
     * @param token token
     * @return boolean
     */
    public boolean renewal(String userType,Long userId,String token){

        String redisKey;
        long expireTime;

        //TODO 根据用户类型获取redisKey和过期时间
        if (ObjectUtil.isEmpty(token)) {
            log.warn("token 不存在，重新登录");
            return false;
        }
        //获取当前时间
        log.debug("自动续期");
         //记录时间
        return true;
    }
}

