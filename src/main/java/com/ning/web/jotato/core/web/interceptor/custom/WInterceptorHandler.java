package com.ning.web.jotato.core.web.interceptor.custom;


import com.ning.web.jotato.common.annotion.AnnotationUtil;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.i18n.I18NUtil;
import com.ning.web.jotato.common.spring.SpringContextUtil;
import com.ning.web.jotato.core.annotion.WIntercepts;
import com.ning.web.jotato.core.web.context.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class WInterceptorHandler {
    private static final Logger logger = LoggerFactory.getLogger(WInterceptorHandler.class);

    public WInterceptorHandler() {
    }

    public void doBeforeInterceptor(ActionContext context) {
        WIntercepts interceptor = null;
        interceptor = (WIntercepts) AnnotationUtil.findClassAnnotation(context.getResourceClass(), WIntercepts.class);
        if (interceptor != null) {
            this.doInterceptor(interceptor.before(), context);
        }

        interceptor = (WIntercepts)AnnotationUtil.findMethodAnnotation(context.getInvokeMethod(), WIntercepts.class);
        if (interceptor != null) {
            this.doInterceptor(interceptor.before(), context);
        }

    }

    public void doAfterInterceptor(ActionContext context) {
        WIntercepts interceptor = null;
        interceptor = (WIntercepts)AnnotationUtil.findMethodAnnotation(context.getInvokeMethod(), WIntercepts.class);
        if (interceptor != null) {
            this.doInterceptor(interceptor.after(), context);
        }

        interceptor = (WIntercepts)AnnotationUtil.findClassAnnotation(context.getResourceClass(), WIntercepts.class);
        if (interceptor != null) {
            this.doInterceptor(interceptor.after(), context);
        }

    }

    private void doInterceptor(Class<?>[] clazzs, ActionContext context) {
        if (clazzs != null && clazzs.length != 0) {
            Class[] var3 = clazzs;
            int var4 = clazzs.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<?> clazz = var3[var5];
                this.doInterceptor(clazz, context);
            }

        }
    }

    private void doInterceptor(Class<?> clazz, ActionContext context) {
        try {
            WInterceptor interceptor = (WInterceptor) SpringContextUtil.getBean(clazz);
            interceptor.doIntercept(context);
        } catch (RestException var4) {
            throw var4;
        } catch (Throwable var5) {
            logger.error("Executing WInterceptor:{} error：", var5, clazz.getName());
            throw new RestException("SYS009", I18NUtil.getMessage("SYS009"));
        }
    }
}