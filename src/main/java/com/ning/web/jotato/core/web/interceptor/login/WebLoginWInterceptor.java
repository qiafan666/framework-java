package com.ning.web.jotato.core.web.interceptor.login;

import com.alibaba.fastjson.JSON;
import com.ning.web.jotato.common.constants.Consts;
import com.ning.web.jotato.common.constants.TokenConsts;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.holder.HttpHolder;
import com.ning.web.jotato.common.utils.AESUtil;
import com.ning.web.jotato.core.support.token.TokenUtil;
import com.ning.web.jotato.core.support.token.TokenRenewal;
import com.ning.web.jotato.core.web.context.ActionContext;
import com.ning.web.jotato.core.web.interceptor.custom.WInterceptor;
import com.ning.web.jotato.core.web.session.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Component
public class WebLoginWInterceptor implements WInterceptor {

    private Set<String> doNotNeedLogin = null;

    @Resource
    private TokenRenewal tokenRenewal;

    @Override
    public void doIntercept(ActionContext actionContext) {
        String path = actionContext.getPathInfo();
        load();
        if (!doNotNeedLogin.contains(path)) {

            String userToken = HttpHolder.getUserToken();
            if (StringUtils.isEmpty(userToken)) {
                throw new RestException("UE00001");
            }



            String aesToken = AESUtil.decrypt(Consts.RS, userToken, AESUtil.EncryptMode.ECB);
            //token
            String userInfo = TokenUtil.verifyToken(aesToken, TokenConsts.TOKEN_DTO, TokenConsts.PUBLIC_KEY);
            if (StringUtils.isEmpty(userInfo)) {
                throw new RestException("UE00001");
            }

            SessionUser user = JSON.parseObject(userInfo, SessionUser.class);
            if (Objects.isNull(user) ||  Objects.isNull(user.getUserId())) {
                throw new RestException("UE00001");
            }

            //token续期
//            boolean renewal = tokenRenewal.renewal(Consts.SYS_USER_TYPE.ADMIN_USER, user.getUserId(),userToken);
//            if (!renewal) {
//                throw new RestException("UE00001");
//            }

        }
    }

    private void load() {
        if (doNotNeedLogin == null) {
            // 放行不需要登录的url
            doNotNeedLogin = new HashSet<>();
        }
    }
}
