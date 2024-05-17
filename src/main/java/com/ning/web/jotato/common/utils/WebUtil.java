package com.ning.web.jotato.common.utils;


import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebUtil {
    public WebUtil() {
    }

    public static int getHttpPort(HttpServletRequest req) {
        try {
            return (new URL(req.getRequestURL().toString())).getPort();
        } catch (MalformedURLException var2) {
            var2.printStackTrace();
            return 80;
        }
    }

    public static String getQueryUrl(HttpServletRequest request) {
        return request.getContextPath() + request.getServletPath() + "?" + request.getQueryString();
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie ck = var3[var5];
                if (StringUtils.equalsIgnoreCase(name, ck.getName())) {
                    return ck;
                }
            }

            return null;
        }
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie ck = var3[var5];
                if (StringUtils.equalsIgnoreCase(name, ck.getName())) {
                    return ck.getValue();
                }
            }

            return null;
        }
    }

    public static Integer getInteger(HttpServletRequest request, String paramStr) {
        String value = request.getParameter(paramStr);
        return value != null && !"".equals(value) ? Integer.valueOf(value) : null;
    }

    public static Integer getInteger(HttpServletRequest request, String paramStr, Integer defaultValue) {
        Integer result = getInteger(request, paramStr);
        return result == null ? defaultValue : result;
    }

    public static Long getLong(HttpServletRequest request, String paramStr) {
        String value = request.getParameter(paramStr);
        return value != null && !"".equals(value) ? Long.valueOf(value) : null;
    }

    public static Long getLong(HttpServletRequest request, String paramStr, Long defaultValue) {
        Long result = getLong(request, paramStr);
        return result == null ? defaultValue : result;
    }

    public static String getString(HttpServletRequest request, String paramStr) {
        return request.getParameter(paramStr);
    }

    public static String getString(HttpServletRequest request, String paramStr, String defaultValue) {
        String result = getString(request, paramStr);
        return result == null ? defaultValue : result;
    }

    public static Map<String, Object> getParameters(HttpServletRequest request) {
        Map<String, Object> param = new ConcurrentHashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            param.put(paramName, request.getParameter(paramName));
        }

        return param;
    }

    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        return ip;
    }

    public static String getLocalIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception var1) {
            var1.printStackTrace();
            return "127.0.0.1";
        }
    }
}
