package com.ning.web.jotato.core.support.util;

import com.ning.web.jotato.base.enums.CharsetEnum;
import com.ning.web.jotato.base.utils.StringPool;
import com.ning.web.jotato.common.utils.AESUtil;
import com.ning.web.jotato.core.config.DefaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class EncryptManager {

    @Resource
    private DefaultConfig defaultConfig;

    /**
     * 解密：手机号，或邮箱，或密码
     *
     * @param content
     * @return String
     */
    public String decrypt(String content) {

        if (null == content) {
            return null;
        } else if (StringPool.EMPTY.equals(content)) {
            return StringPool.EMPTY;
        }

        try {
            return AESUtil.decrypt(defaultConfig.getAesKey(), URLDecoder.decode(content, CharsetEnum.UTF_8.getCode()), AESUtil.EncryptMode.ECB);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 加密：手机号，或邮箱，或密码
     *
     * @param content
     * @return String
     */
    public String encrypt(String content) {

        if (null == content) {
            return null;
        } else if (StringPool.EMPTY.equals(content)) {
            return StringPool.EMPTY;
        }

        try {
            return URLEncoder.encode(AESUtil.encrypt(defaultConfig.getAesKey(), content, AESUtil.EncryptMode.ECB), CharsetEnum.UTF_8.getCode());
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public String replacePhone(String phone) {

        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public String replaceEmail(String email) {
        Pattern pattern = Pattern.compile("@");
        Matcher matcher = pattern.matcher(email);
        matcher.find();
        Integer start = matcher.start();
        if(start > 4) {
            StringBuilder sb = new StringBuilder(email.substring(0, 3));
            sb.append("****").append(email.substring(start-1, email.length()));
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            switch (start) {
                case 0:
                    return "illegal email";
                case 1:
                case 2:
                    return sb.append(email.substring(0, 1)).append("****").append(email.substring(start, email.length())).toString();
                default:
                    return sb.append(email.substring(0, 1)).append("****").append(email.substring(start-1, email.length())).toString();
            }
        }
    }
}
