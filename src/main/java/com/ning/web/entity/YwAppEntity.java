package com.ning.web.entity;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("yw_app")
@ApiModel(value="yw_app表", description="应用表")
public class YwAppEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value="app_name")
    @ApiModelProperty(value = "应用名称")
    private String appName;
    @TableField(value="icon")
    @ApiModelProperty(value = "图标")
    private String icon;
    @TableField(value="describe")
    @ApiModelProperty(value = "描述")
    private String describe;
    @TableField(value="style")
    @ApiModelProperty(value = "状态")
    private String style;
    @TableField(value="address")
    @ApiModelProperty(value = "地址")
    private String address;
    @TableField(value="webroot")
    @ApiModelProperty(value = "webroot")
    private String webroot;
    @TableField(value="index")
    @ApiModelProperty(value = "首页")
    private String index;
    @TableField(value="nginx_address")
    @ApiModelProperty(value = "代理地址")
    private String nginxAddress;
    @TableField(value="nginx_port")
    @ApiModelProperty(value = "代理端口")
    private String nginxPort;
    @TableField(value="api_style")
    @ApiModelProperty(value = "api应用状态")
    private String apiStyle;
    @TableField(value="nginx_protocol")
    @ApiModelProperty(value = "代理协议")
    private String nginxProtocol;

    @TableField(value="http_status")
    @ApiModelProperty(value = "HTTP状态")
    private String httpStatus;
    @TableField(value="classify_status")
    @ApiModelProperty(value = "分类状态")
    private String classifyStatus;
    @TableField(value="databaselog_status")
    @ApiModelProperty(value = "数据库日志状态")
    private String databaselogStatus;
    @TableField(value="apilog_status")
    @ApiModelProperty(value = "API日志状态")
    private String apilogStatus;

    @TableField(value="enable_status")
    @ApiModelProperty(value = "开启状态")
    private String enableStatus;
    @TableField(value="session_timeout")
    @ApiModelProperty(value = "会话超时时间")
    private Integer sessionTimeout;
    @TableField(value="session_monitor")
    @ApiModelProperty(value = "会话监控")
    private String sessionMonitor;
    @TableField(value="session_control")
    @ApiModelProperty(value = "会话监控")
    private String sessionControl;
    @TableField(value="session_send_message")
    @ApiModelProperty(value = "发送消息")
    private String sessionSendMessage;
    @TableField(value="session_video")
    @ApiModelProperty(value = "会话录像")
    private String sessionVideo;
    @TableField(value="session_log")
    @ApiModelProperty(value = "会话日志")
    private String sessionLog;
    @TableField(value="session_script")
    @ApiModelProperty(value = "会话监控")
    private String sessionScript;
    @TableField(value="cache_status")
    @ApiModelProperty(value = "缓存状态")
    private String cacheStatus;
    @TableField(value="pem")
    @ApiModelProperty(value = "pem")
    private String pem;
    @TableField(value="key")
    @ApiModelProperty(value = "key")
    private String key;
    @TableField(value="gateway_server")
    @ApiModelProperty(value = "GW-Server块")
    private String gatewayServer;
    @TableField(value="gateway_webrootlocation")
    @ApiModelProperty(value = "GW-WebrootLocation块")
    private String gatewayWebrootlocation;
    @TableField(value="script_ids")
    @ApiModelProperty(value = "脚本主键ids")
    private String scriptIds;

    @TableField(value="is_deleted")
    private Integer isDeleted;

    @TableField(value="updated_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @TableField(value="created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

    private static final long serialVersionUID = 1L;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot == null ? null : webroot.trim();
    }

    public void setIndex(String index) {
        this.index = index == null ? null : index.trim();
    }

    public void setNginxAddress(String nginxAddress) {
        this.nginxAddress = nginxAddress == null ? null : nginxAddress.trim();
    }

    public void setNginxPort(String nginxPort) {
        this.nginxPort = nginxPort == null ? null : nginxPort.trim();
    }

    public void setApiStyle(String apiStyle) {
        this.apiStyle = apiStyle == null ? null : apiStyle.trim();
    }

    public void setNginxProtocol(String nginxProtocol) {
        this.nginxProtocol = nginxProtocol == null ? null : nginxProtocol.trim();
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus == null ? null : enableStatus.trim();
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setSessionMonitor(String sessionMonitor) {
        this.sessionMonitor = sessionMonitor == null ? null : sessionMonitor.trim();
    }

    public void setSessionControl(String sessionControl) {
        this.sessionControl = sessionControl == null ? null : sessionControl.trim();
    }

    public void setSessionSendMessage(String sessionSendMessage) {
        this.sessionSendMessage = sessionSendMessage == null ? null : sessionSendMessage.trim();
    }

    public void setSessionVideo(String sessionVideo) {
        this.sessionVideo = sessionVideo == null ? null : sessionVideo.trim();
    }

    public void setSessionLog(String sessionLog) {
        this.sessionLog = sessionLog == null ? null : sessionLog.trim();
    }

    public void setSessionScript(String sessionScript) {
        this.sessionScript = sessionScript == null ? null : sessionScript.trim();
    }

    public void setCacheStatus(String cacheStatus) {
        this.cacheStatus = cacheStatus == null ? null : cacheStatus.trim();
    }

    public void setPem(String pem) {
        this.pem = pem == null ? null : pem.trim();
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public void setGatewayServer(String gatewayServer) {
        this.gatewayServer = gatewayServer == null ? null : gatewayServer.trim();
    }

    public void setGatewayWebrootlocation(String gatewayWebrootlocation) {
        this.gatewayWebrootlocation = gatewayWebrootlocation == null ? null : gatewayWebrootlocation.trim();
    }

    public void setScriptIds(String scriptIds) {
        this.scriptIds = scriptIds == null ? null : scriptIds.trim();
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}