package com.ning.web.jotato.core.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ActionContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String inPacket;
    private String outPacket;
    private String path;
    private String pathInfo;
    private String contentType;
    private String httpMethod;

    private String authToken;

    private Class<?> resourceClass;

    private Method invokeMethod;
    private String requestId;
    private long startTime;
    private long endTime;

    public ActionContext() {
    }


    public Class<?> getResourceClass() {
        return this.resourceClass;
    }

    public void setResourceClass(Class<?> resourceClass) {
        this.resourceClass = resourceClass;
    }

    public Method getInvokeMethod() {
        return this.invokeMethod;
    }

    public void setInvokeMethod(Method invokeMethod) {
        this.invokeMethod = invokeMethod;
    }
    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getInPacket() {
        return this.inPacket;
    }

    public void setInPacket(String inPacket) {
        this.inPacket = inPacket;
    }

    public String getOutPacket() {
        return this.outPacket;
    }

    public void setOutPacket(String outPacket) {
        this.outPacket = outPacket;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathInfo() {
        return this.pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
