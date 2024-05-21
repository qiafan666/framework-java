package com.ning.web.project.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Slf4j
public class TokenUtil {

    private TokenUtil() {}

    private static final String secret = "This secret is Used for Signing userToken";
    private static final String issuer = "etet";

	private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    public static Map<String, Object> getDataMapByToken(String token) {
//        if (null == token || "".equals(token.trim())) {
//            return null;
//        }
//        try {
//            final JWTVerifier verifier = new JWTVerifier(secret);
//            final Map<String, Object> claims = verifier.verify(token);
//            final long iat = System.currentTimeMillis() / 1000L;
//            Long exp = Long.valueOf(claims.get("exp").toString());
//            if (iat <= exp) {
//                return claims;
//            }
//        } catch (Exception e) {
//            // Invalid Token
//            logger.error("{}", e);
//        }
        return null;
    }

    public static String signJWT(String userId, String userName, String userNo){
//        final long iat = System.currentTimeMillis() / 1000L;
//
//        //final long exp = iat + 1800000000L; //10小时
//        final long exp = iat + 7*24*60*60L;
//
//        final JWTSigner signer = new JWTSigner(secret);
//        final HashMap<String, Object> claims = new HashMap<String, Object>();
//        claims.put("iss", issuer);
//        claims.put("exp", exp);
//        claims.put("iat", iat);
//        claims.put(TokenConsts.USER_ID, userId);
//        claims.put(TokenConsts.USER_NAME, userName);
//        claims.put(TokenConsts.USER_NO, userNo);
//        final String jwt = signer.sign(claims);
//        return jwt;
        return null;
    }

    public static Boolean verifyJWT(String token) {
//        try {
//            final JWTVerifier verifier = new JWTVerifier(secret);
//            verifier.verify(token);
//            return true;
//        } catch (Exception e) {
//            log.error("{}", e);
//        }
        return false;
    }

    public static Long getUserNoByToken(String token) {
//        try {
//            final JWTVerifier verifier = new JWTVerifier(secret);
//            final Map<String, Object> claims = verifier.verify(token);
//
//            Long uid = Long.valueOf(claims.get("uid").toString());
//            return uid;
//        } catch (Exception e) {
//            log.error("{}", e);
//        }
        return -1L;
    }

}
