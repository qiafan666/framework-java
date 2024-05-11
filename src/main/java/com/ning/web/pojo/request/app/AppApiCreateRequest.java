package com.ning.web.pojo.request.app;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value = "应用API创建参数",description = "应用API创建参数")
public class AppApiCreateRequest {

    @TableField(value="api_name")
    @ApiModelProperty(value = "api名称")
    private String apiName;

    @TableField(value="api_service_name")
    @ApiModelProperty(value = "服务名称")
    private String apiServiceName;

    @TableField(value="protocol")
    @ApiModelProperty(value = "协议")
    private String protocol;

    @TableField(value="host")
    @ApiModelProperty(value = "host")
    private String host;

    @TableField(value="port")
    @ApiModelProperty(value = "端口")
    private String port;

    @TableField(value="方法")
    @ApiModelProperty(value = "方法")
    private String method;

    @TableField(value="webroot")
    @ApiModelProperty(value = "webroot")
    private String webroot;

    @TableField(value="app_id")
    @ApiModelProperty(value = "应用主键id")
    private Long appId;

    @TableField(value="is_deleted")
    private Integer isDeleted;

    @TableField(value="updated_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @TableField(value="created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

}
