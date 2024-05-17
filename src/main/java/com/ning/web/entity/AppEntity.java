package com.ning.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 应用表
 * </p>
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
@Getter
@Setter
@TableName("yw_app")
public class AppEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 应用名称
     */
    @TableField("app_name")
    private String appName;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 描述
     */
    @TableField("describe")
    private String describe;

    /**
     * 应用状态（枚举：online offline）
     */
    @TableField("app_status")
    private String appStatus;

    /**
     * 应用地址
     */
    @TableField("address")
    private String address;

    /**
     * webroot
     */
    @TableField("webroot")
    private String webroot;

    /**
     * 首页
     */
    @TableField("index")
    private String index;

    /**
     * 代理地址
     */
    @TableField("nginx_address")
    private String nginxAddress;

    /**
     * 代理端口
     */
    @TableField("nginx_port")
    private String nginxPort;

    /**
     * api应用（枚举 yes:是api应用 no：不是）
     */
    @TableField("api_app")
    private String apiApp;

    /**
     * 代理协议
     */
    @TableField("nginx_protocol")
    private String nginxProtocol;

    /**
     * http报文状态（枚举：enable disable）
     */
    @TableField("http_status")
    private String httpStatus;

    /**
     * 分类分级对接状态（枚举：enable disable）
     */
    @TableField("classify_status")
    private String classifyStatus;

    /**
     * 数据库日志对接状态（枚举：enable disable）
     */
    @TableField("databaselog_status")
    private String databaselogStatus;

    /**
     * API日志对接状态（枚举：enable disable）
     */
    @TableField("apilog_status")
    private String apilogStatus;

    /**
     * 开启状态（枚举：enable disable）
     */
    @TableField("enable_status")
    private String enableStatus;

    /**
     * 会话话超时时间（单位：秒）
     */
    @TableField("session_timeout")
    private Integer sessionTimeout;

    /**
     * 监控状态（枚举：enable disable）
     */
    @TableField("session_monitor")
    private String sessionMonitor;

    /**
     * 控制状态（枚举：enable&notice enable&not_notice  disable）
     */
    @TableField("session_control")
    private String sessionControl;

    /**
     * 监控状态（枚举：enable disable）
     */
    @TableField("session_send_message")
    private String sessionSendMessage;

    /**
     * 录像状态（枚举：enable disable）
     */
    @TableField("session_video")
    private String sessionVideo;

    /**
     * 日志打印（枚举：enable disable）
     */
    @TableField("session_log")
    private String sessionLog;

    /**
     * 基础脚本（枚举：enable disable）
     */
    @TableField("session_script")
    private String sessionScript;

    /**
     * 缓存状态（枚举：enable disable）
     */
    @TableField("cache_status")
    private String cacheStatus;

    /**
     * pem
     */
    @TableField("pem")
    private String pem;

    /**
     * key
     */
    @TableField("key")
    private String key;

    /**
     * GW-Server块
     */
    @TableField("gateway_server")
    private String gatewayServer;

    /**
     * GW-WebrootLocation块
     */
    @TableField("gateway_webrootlocation")
    private String gatewayWebrootlocation;

    /**
     * 脚本主键ids（example：€1;€2;）
     */
    @TableField("script_ids")
    private String scriptIds;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;


}
