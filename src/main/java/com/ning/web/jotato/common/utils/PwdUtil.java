package com.ning.web.jotato.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 应用于JDK1.8下
 *
 * @author zhaoyang
 *
 */
public class PwdUtil {
    private static Logger log = LoggerFactory.getLogger(PwdUtil.class);

    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private PwdUtil() {}

    public static String getPBKDF2Encryption(String password) {

        byte[] dk = null;
        dk = PBKDF2.deriveKey(password);
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(dk);
    }

    public static boolean verifyPBKDF2(String pwd, String encryptPwd) {

        Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(encryptPwd);

        return PBKDF2.verify(pwd, buffer);
    }


    public static String getMD5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = DIGITS_LOWER[byte0 >>> 4 & 15];
                str[k++] = DIGITS_LOWER[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var9) {
            log.error("MD5加密失败.Exception={0}",var9);
            return null;
        }
    }
}
