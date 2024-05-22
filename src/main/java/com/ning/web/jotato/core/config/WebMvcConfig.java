package com.ning.web.jotato.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ning.web.jotato.common.constants.SysConstant;
import com.ning.web.jotato.common.env.EnvUtil;
import com.ning.web.jotato.core.web.converter.WMessageConverter;
import com.ning.web.jotato.core.web.interceptor.spring.SpringInterceptorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    @Autowired(
            required = false
    )
    private List<HandlerInterceptor> handlerInterceptors;
    @Autowired
    private WMessageConverter messageConverter;

    public WebMvcConfig() {
    }


    @Bean
    public CorsFilter corsFilter() {
        // TODO 跨域生产要关掉
        // 设置允许跨域请求的域名
        CorsConfiguration config = new CorsConfiguration();
        // 是否允许证书 不再默认开启 //
        config.addAllowedOrigin("*");
        // 设置允许的方法
        config.setAllowCredentials(true);
        // 允许任何头
        config.addAllowedMethod("GET");
        // 允许任何头
        config.addAllowedMethod("POST");
        // 允许任何头
        config.addAllowedMethod("OPTIONS");
        // 预检请求头信息描述 //
        config.addAllowedHeader("*");
        config.addExposedHeader("token");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(SysConstant.DEFAULT_CHARSET);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        String defaultLanguage = EnvUtil.getProperty("spring.messages.default-language");
        if (StringUtils.hasText(defaultLanguage)) {
            localeResolver.setDefaultLocale(Locale.forLanguageTag(defaultLanguage));
        }

        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.messageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.localeChangeInterceptor());
        if (this.handlerInterceptors != null) {
            Iterator var2 = this.handlerInterceptors.iterator();

            while(var2.hasNext()) {
                HandlerInterceptor handlerInterceptor = (HandlerInterceptor)var2.next();
                InterceptorRegistration registration = registry.addInterceptor(handlerInterceptor);
                if (handlerInterceptor instanceof SpringInterceptorAdapter) {
                    SpringInterceptorAdapter interceptor = (SpringInterceptorAdapter)handlerInterceptor;
                    registration.addPathPatterns(interceptor.includePatterns());
                    registration.excludePathPatterns(interceptor.excludePatterns());
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("Registered HandlerInterceptor: " + handlerInterceptor.getClass());
                }
            }
        }

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        final FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        final FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        int index = 0;
        for (int i = 0; i < converters.size(); i++) {
            index = i;
            if (converters.get(i).getSupportedMediaTypes().contains(MediaType.APPLICATION_JSON)) {
                break;
            }
        }
        converters.add(index, fastConverter);
    }
}
