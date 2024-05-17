package com.ning.web.jotato.core.web.session;

import java.util.Date;

public class SessionContext {

    private static final ThreadLocal<SessionContext> SESSION_CONTEXT = ThreadLocal.withInitial(SessionContext::new);

    private SessionUser sessionUser;
    private Date createTime;
    private String ip;

    private SessionContext() {
    }

    public static SessionContext current() {
        return SESSION_CONTEXT.get();
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void clean() {
        this.createTime = null;
        this.sessionUser = null;
        this.ip = null;
        SESSION_CONTEXT.remove();
    }
}
