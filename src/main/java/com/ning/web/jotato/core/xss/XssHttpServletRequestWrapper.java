package com.ning.web.jotato.core.xss;

import com.ning.web.jotato.base.enums.CharsetEnum;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by ning on 2020/11/16.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }


    /**
     * 没有违规的数据，就返回false;
     *
     * @param pattern 表达式
     * @return boolean
     */
    private boolean checkHeader(Pattern pattern) {
        Enumeration<String> headerParams = this.getHeaderNames();
        while (headerParams.hasMoreElements()) {
            String headerName = headerParams.nextElement();
            String headerValue = this.getHeader(headerName);
            if (pattern.matcher(headerValue).matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 没有违规的数据，就返回false;
     *
     * @param pattern 表达式
     * @return boolean
     */
    private boolean checkParameter(Pattern pattern) throws UnsupportedEncodingException {
        Map<String, String[]> submitParams = this.getParameterMap();

        for (Map.Entry<String, String[]> entry : submitParams.entrySet()) {
            String[] submitValues = entry.getValue();

            if (Objects.isNull(submitValues)) {
                continue;
            }

            for (String submitValue : submitValues) {
                if (pattern.matcher(URLDecoder.decode(submitValue, CharsetEnum.UTF_8.getCode())).matches()) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 没有违规的数据，就返回false;
     * 若存在违规数据，根据配置信息判断是否跳转到错误页面
     *
     * @param pattern 表达式
     * @return boolean
     * @throws IOException 转码异常
     */
    public boolean validateParameter(Pattern pattern) throws IOException {
        // 开始header校验，对header信息进行校验
        if (this.checkHeader(pattern)) {
            return true;
        }
        // 开始parameter校验，对parameter信息进行校验
        return this.checkParameter(pattern);
    }


}
