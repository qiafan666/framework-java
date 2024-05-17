package com.ning.web.jotato.core.web.interceptor;

import com.ning.web.jotato.core.support.logger.LogContext;
import com.ning.web.jotato.core.web.context.ActionContext;
import com.ning.web.jotato.core.web.context.ActionContextBuilder;
import com.ning.web.jotato.core.web.converter.WMessageConverter;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;
import java.util.UUID;

@Component
@Order(-2)
public class SysInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private WMessageConverter messageConverter;

    public SysInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 设置输出编码格式，如有需要更改text/json返回类型请自行在相应方法中进行修改 cml-20171220
        //response.setHeader("Content-type", "text/json;charset=UTF-8");
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST");


        String requestId = request.getHeader("RequestId");
        if (StringUtils.isEmpty(requestId)) {
            requestId =  UUID.randomUUID().toString().replace("-", "");
            LogContext.put(requestId);
            System.out.println("requestId = " + MDC.get("requestId"));
        }

        LogContext.put(requestId);
        String timezone = request.getHeader("Accept-Timezone");
        if (StringUtils.hasText(timezone)) {
            LocaleContextHolder.setTimeZone(TimeZone.getTimeZone(timezone));
        }

        if (handler instanceof HandlerMethod) {
            long startTime = System.currentTimeMillis();
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            ActionContext context = ActionContextBuilder.build();
            context.setStartTime(startTime);
            context.setRequestId(LogContext.get());
            context.setRequest(request);
            context.setResponse(response);
            context.setPath(request.getContextPath());
            context.setPathInfo(request.getRequestURI().substring(1));
            context.setContentType(request.getContentType());
            context.setHttpMethod(request.getMethod());
            context.setResourceClass(handlerMethod.getBeanType());
            context.setInvokeMethod(handlerMethod.getMethod());
            boolean notAn = true;
            MethodParameter[] var11 = handlerMethod.getMethodParameters();
            int var12 = var11.length;

            for(int var13 = 0; var13 < var12; ++var13) {
                MethodParameter parameter = var11[var13];
                if (parameter.hasParameterAnnotation(RequestBody.class)) {
                    notAn = false;
                    break;
                }
            }

            if (notAn) {
                this.messageConverter.resolveArgument(request);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ActionContextBuilder.destroy();
        LogContext.destroy();
    }
}