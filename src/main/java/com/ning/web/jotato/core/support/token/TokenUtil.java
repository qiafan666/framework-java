package com.ning.web.jotato.core.support.token;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ning.web.jotato.common.constants.TokenConsts;
import com.ning.web.jotato.core.web.session.SessionUser;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil{


    //过期时间
    private static final Integer TIME_OUT_DAY = 5;
    //tokenActiveDays 过期时间

    // SessionUser 默认的创建token 通过 SessionUser.newSessionUser()
    public static String defaultCreateToken(Integer tokenActiveDays, SessionUser sessionUser) {
        Calendar calendar = Calendar.getInstance();
        if (tokenActiveDays==null||tokenActiveDays==0) {
            tokenActiveDays = TIME_OUT_DAY;
        }
        calendar.add(Calendar.DATE,tokenActiveDays);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null;
        token = JWT.create()
                .withHeader(map)                        // header
                .withClaim(TokenConsts.TOKEN_DTO, JSON.toJSONString(sessionUser))// payload
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(TokenConsts.PUBLIC_KEY));   // 加密

        return token;
    }

    //默认的验证token
    public static String defaultVerifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TokenConsts.PUBLIC_KEY)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return claims.get(TokenConsts.TOKEN_DTO).asString();
        } catch (Exception e) {
            log.warn("登录凭证无效或已过期，请重新登录", e);
        }
        return null;
    }

    // 自定义创建token
    // tokkenActiveDays 过期时间
    // key 存入的key
    // value 存入的value json字符串
    // publicKey 公钥
    public static String createToken(Integer tokenActiveDays,String key, String value, String publicKey) {
        Calendar calendar = Calendar.getInstance();
        if (tokenActiveDays==null||tokenActiveDays==0) {
            tokenActiveDays = TIME_OUT_DAY;
        }
        calendar.add(Calendar.DATE,tokenActiveDays);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null;
        token = JWT.create()
                .withHeader(map)                        // header
                .withClaim(key, value)                  // payload
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(publicKey));   // 加密

        return token;
    }

    // 自定义验证token
    public static String verifyToken(String token, String key, String publicKey) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(publicKey)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return claims.get(key).asString();
        } catch (Exception e) {
            log.warn("登录凭证无效或已过期，请重新登录", e);
        }
        return null;
    }

}
