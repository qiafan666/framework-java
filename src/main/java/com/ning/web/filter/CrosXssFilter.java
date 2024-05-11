package com.ning.web.filter;

import com.alibaba.fastjson.JSON;
import com.ning.web.base.bean.IResultCode;
import com.ning.web.base.common.CommonResult;
import com.ning.web.filter.xss.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@Slf4j
@WebFilter(value = "/*")
@Order(2)
public class CrosXssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("sql过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        //sql,xss过滤
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(httpServletRequest);
        //获得所有请求参数名
        Enumeration<String> names = request.getParameterNames();
        String sql = "";
        while (names.hasMoreElements()){
            //得到参数名
            String name = names.nextElement().toString();
            //得到参数对应值
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                sql += values[i];
            }
        }
        if (sqlValidate(sql)){
            outReponse(httpResponse,"含有非法字符");
        } else {
            log.info("crosXssFilter..........doFilter url:{},ParameterMap:{}",xssHttpServletRequestWrapper.getRequestURI(), JSON.toJSON(xssHttpServletRequestWrapper.getParameterMap()));
            chain.doFilter(xssHttpServletRequestWrapper, httpResponse);
        }
    }

    /**
     * create by:  zhouxq
     * description: 打印输出
     * create time: 2022/6/11/011 0:22
     * @params [httpResponse, errMsg]
     * @return void
     */
    private void outReponse(HttpServletResponse response,String errMsg) {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        CommonResult resp = CommonResult.failed(IResultCode.ILLEGAL_ARGUMENT,errMsg);
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.print(JSON.toJSONString(resp));
            log.info("crosXssFilter 返回：{}",resp.toString());
        }catch (IOException e){
            log.error("异常错误：",e);
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    /**
     * create by:  zhouxq
     * description: 校验数据
     * create time: 2022/6/11/011 0:41
     * @params [str]
     * @return boolean
     */
    protected static boolean sqlValidate(String str){
        String s = str.toLowerCase();//统一转为小写
        //过滤掉的sql关键字，特殊字符前面需要加\\进行转义
        String badStr =
                "select|update|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|table|"+
                        "char|declare|sitename|xp_cmdshell|like|from|grant|use|group_concat|column_name|" +
                        "information_schema.columns|table_schema|union|where|order|by|" +
                        "'\\*|\\;|\\-|\\--|\\+|\\,|\\//|\\/|\\%|\\#";
        //使用正则表达式进行匹配
        boolean matches = s.matches(badStr);
        return matches;
    }

    @Override
    public void destroy() {

    }
}
