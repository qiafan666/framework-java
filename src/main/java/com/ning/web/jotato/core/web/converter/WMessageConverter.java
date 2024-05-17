package com.ning.web.jotato.core.web.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ning.web.jotato.base.model.result.BaseBean;
import com.ning.web.jotato.common.constants.SysConstant;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.utils.WebUtil;
import com.ning.web.jotato.core.request.BaseReq;
import com.ning.web.jotato.core.support.logger.LogPrinter;
import com.ning.web.jotato.core.web.context.ActionContext;
import com.ning.web.jotato.core.web.context.ActionContextBuilder;
import com.ning.web.jotato.core.web.interceptor.custom.WInterceptorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WMessageConverter extends FastJsonHttpMessageConverter implements HandlerMethodArgumentResolver {
    @Autowired
    private LogPrinter logPrinter;
/*    @Autowired
    private WMessageValidator messageValidator;*/
    @Autowired
    private WInterceptorHandler interceptorHandler;
    private static final ThreadLocal<Object> afterInterceptorExecCache = new ThreadLocal();

    public WMessageConverter() {
    }

    @PostConstruct
    public void init() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(SysConstant.DEFAULT_CHARSET);
        fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect});
        List<MediaType> mediaTypes = new ArrayList();
        mediaTypes.add(new MediaType(MediaType.TEXT_HTML, SysConstant.DEFAULT_CHARSET));
        mediaTypes.add(new MediaType(MediaType.TEXT_PLAIN, SysConstant.DEFAULT_CHARSET));
        mediaTypes.add(new MediaType(MediaType.APPLICATION_JSON, SysConstant.DEFAULT_CHARSET));
        mediaTypes.add(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, SysConstant.DEFAULT_CHARSET));
        super.setDefaultCharset(SysConstant.DEFAULT_CHARSET);
        super.setFastJsonConfig(fastJsonConfig);
        super.setSupportedMediaTypes(mediaTypes);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class);
        Map<String, Object> params = WebUtil.getParameters(request);
        if (params != null && !params.isEmpty()) {
            String data = this.readInternal(this.toJSONString(params));
            return this.toObject(data, parameter.getParameterType());
        } else {
            HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
            return this.readInternal(parameter.getParameterType(), inputMessage);
        }
    }

    public String resolveArgument(HttpServletRequest request) {
        Map<String, Object> params = WebUtil.getParameters(request);
        return params != null && !params.isEmpty() ? this.readInternal(this.toJSONString(params)) : this.readInternal((String)null);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Class<?> paramType = Object.class;

        try {
            Type t = GenericTypeResolver.resolveType(type, contextClass);
            paramType = Class.forName(t.getTypeName());
        } catch (Exception var6) {
            this.logger.warn(var6);
        }

        return this.readInternal(paramType, inputMessage);
    }

    @Override
    public Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Charset charset = this.getContentTypeCharset(inputMessage.getHeaders().getContentType());
        String text = StreamUtils.copyToString(inputMessage.getBody(), charset);
        String data = this.readInternal(text);
        return this.toObject(data, clazz);
    }

    private String readInternal(String text) {
        ActionContext actionContext = ActionContextBuilder.build();
        actionContext.setInPacket(text);
        if (this.logPrinter.hasPrintLog(actionContext)) {
            this.logPrinter.printIn(actionContext);
        }

        this.interceptorHandler.doBeforeInterceptor(actionContext);
        //this.messageValidator.validInMeaasge(actionContext);
        return actionContext.getInPacket();
    }

    @Override
    public void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ActionContext actionContext = ActionContextBuilder.build();
        String jsonString = this.toJSONString(obj);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put("requestId", actionContext.getRequestId());
        actionContext.setOutPacket(jsonObject.toJSONString());
        if (afterInterceptorExecCache.get() == null) {
            try {
                this.interceptorHandler.doAfterInterceptor(actionContext);
            } catch (RestException var6) {
                afterInterceptorExecCache.set(1);
                throw var6;
            }
        } else {
            afterInterceptorExecCache.remove();
        }

        if (this.logPrinter.hasPrintLog(actionContext)) {
            this.logPrinter.printOut(actionContext);
        }

        String data = actionContext.getOutPacket();
        if (data != null) {
            Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());
            StreamUtils.copy(data, charset, outputMessage.getBody());
        }
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        return contentType != null && contentType.getCharset() != null ? contentType.getCharset() : this.getDefaultCharset();
    }

    private Object toObject(String data, Class<?> paramType) {
        if (BaseBean.class.isAssignableFrom(paramType)) {
            try {
                BaseReq reqEntity = (BaseReq)paramType.newInstance();
                return reqEntity.toObject(data);
            } catch (Exception var4) {
                this.logger.error(var4);
                throw new HttpMessageConversionException("Class " + paramType.getName() + " new instance error!", var4);
            }
        } else {
            return String.class.isAssignableFrom(paramType) ? data : JSON.parseObject(data, paramType);
        }
    }

    private String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof String ? (String)obj : JSON.toJSONString(obj, super.getFastJsonConfig().getSerializerFeatures());
        }
    }
}