package com.ning.web.pojo.request.app;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value = "应用数据库创建请求参数",description = "应用数据库创建请求参数")
public class AppDatabaseCreateRequest {

    @TableField(value="name")
    @ApiModelProperty(value = "名称")
    private String name;
    @TableField(value="group_id")
    @ApiModelProperty(value = "分组主键id")
    private Long groupId;
    @TableField(value="style")
    @ApiModelProperty(value = "类型")
    private String type;
    @TableField(value="ip")
    @ApiModelProperty(value = "ip")
    private String ip;
    @TableField(value="ssl_ca")
    @ApiModelProperty(value = "ca证书")
    private String sslCa;
    @TableField(value="ssl_client_key")
    @ApiModelProperty(value = "客户端秘钥")
    private String sslClientKey;
    @TableField(value="ssl_client_cert")
    @ApiModelProperty(value = "数字证书")
    private String sslClientCert;
    @TableField(value="database_name")
    @ApiModelProperty(value = "数据库名")
    private String databaseName;
    @TableField(value="port")
    @ApiModelProperty(value = "端口")
    private String port;
    @TableField(value="database_user")
    @ApiModelProperty(value = "用户")
    private String databaseUser;
    @TableField(value="password")
    @ApiModelProperty(value = "password")
    private String password;
    @TableField(value="classify_id")
    @ApiModelProperty(value = "分类模版id")
    private Long classifyId;
    @TableField(value="layer_id")
    @ApiModelProperty(value = "分级id")
    private Long layerId;
    @TableField(value="sample_num")
    @ApiModelProperty(value = "抽样数量")
    private Integer sampleNum;
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
