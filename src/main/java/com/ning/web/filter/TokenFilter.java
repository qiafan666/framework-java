package com.ning.web.filter;


import com.alibaba.fastjson.JSONObject;

import com.ning.web.base.bean.WebResultCode;
import com.ning.web.base.constants.TokenConsts;
import com.ning.web.base.enums.CharsetEnum;
import com.ning.web.base.common.CommonResult;

import com.ning.web.base.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Slf4j
@WebFilter(filterName = "tokenFilter",urlPatterns = {"/v1/*"})
public class TokenFilter implements Filter {

    private static final String AUTH = "Authorization";
//    private static final String LANGUAGE = "Accept-Language";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 设置输出编码格式，如有需要更改text/json返回类型请自行在相应方法中进行修改 cml-20171220
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding(CharsetEnum.UTF_8.getCode());

        String token = request.getHeader(AUTH);
        if (log.isDebugEnabled()) {
            log.debug("doFilter token={}", token);
        }
        String requestUrl = request.getRequestURI();
        boolean bool = false;
        ERROR_TOKEN: {
            if(requestUrl.contains("/swagger-ui") || requestUrl.contains("/swagger-resources") || requestUrl.contains("/webjars")
                    || requestUrl.contains("/api-docs")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            Map<String, Object> dataMap = TokenUtil.getDataMapByToken(token);
            if (null == dataMap || dataMap.isEmpty()) {
                break ERROR_TOKEN;
            }
            try {
//            	TcUserMapper tcUserMapper = SpringUtil.getBean(TcUserMapper.class);
//            	TcUserEntity tcUserEntity = tcUserMapper.selectById(String.valueOf(dataMap.get(TokenConsts.USER_ID)));
//            	if(Objects.nonNull(tcUserEntity)) {
//            		if(Objects.equals(tcUserEntity.getUserRole(), 1)) {
//            			bool = true;
//            			break ERROR_TOKEN;
//            		}
//            	}
                request.setAttribute(TokenConsts.USER_NO, dataMap.get(TokenConsts.USER_NO));
                request.setAttribute(TokenConsts.USER_ID, dataMap.get(TokenConsts.USER_ID));
                request.setAttribute(TokenConsts.USER_NAME, dataMap.get(TokenConsts.USER_NAME));
            } catch (Exception e) {
                log.error("Userfilter:Jedis -- token处理异常.", e);
                break ERROR_TOKEN;
            }
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(bool) {
        	CommonResult<String> result = new CommonResult<String>(WebResultCode.USER_GEETEST_VALIDATE_FAIL.code(), WebResultCode.USER_GEETEST_VALIDATE_FAIL.message(),"" );
            response.getWriter().print(JSONObject.toJSON(result));
        }else {
        	CommonResult<String> result = new CommonResult<String>(WebResultCode.TOKEN_STATUS_INVALID.code(), WebResultCode.TOKEN_STATUS_INVALID.message(),"" );
            response.getWriter().print(JSONObject.toJSON(result));
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
