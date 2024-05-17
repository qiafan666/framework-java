package com.ning.web.jotato.core.support.logger;


import com.ning.web.jotato.core.web.context.ActionContext;

public interface LogPrinter {

    void printIn(ActionContext context);

    //void printIn(BizLogEntity bizLogEntity);

    void printOut(ActionContext context);

    default boolean hasPrintLog(ActionContext actionContext) {
//        DisableLog disableLog = (DisableLog)AnnotationUtil.findMethodAnnotation(actionContext.getInvokeMethod(), DisableLog.class);
//        if (disableLog == null) {
//            disableLog = (DisableLog)AnnotationUtil.findClassAnnotation(actionContext.getResourceClass(), DisableLog.class);
//        }

        return true;
    }
}
