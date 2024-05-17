package com.ning.web.jotato.common.holder;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class HttpHolder {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        return httpServletRequest.getSession();
    }


    public static String getUserToken() {
        HttpServletRequest request = HttpHolder.getHttpServletRequest();
        return request.getHeader("Authorization");
    }

    public static String getReqStr(HttpServletRequest req, String charset) throws IOException {
        Assert.hasText(charset, "charset must not be null");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = req.getInputStream().read(buf)) > 0) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray(), charset);
    }

    public static String getReqStr(HttpServletRequest req) throws Exception {
        return getReqStr(req, StandardCharsets.UTF_8.name());
    }
}
