package com.ning.web.jotato.core.web.context;

public class ActionContextBuilder {
    private static final ThreadLocal<ActionContext> LOCAL_CONTEXT = new ThreadLocal();

    public ActionContextBuilder() {
    }

    public static ActionContext build() {
        ActionContext context = (ActionContext)LOCAL_CONTEXT.get();
        if (context == null) {
            context = new ActionContext();
            LOCAL_CONTEXT.set(context);
        }

        return context;
    }

    public static void destroy() {
        LOCAL_CONTEXT.remove();
    }
}
