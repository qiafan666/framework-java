package com.ning.web.pojo.request.app;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "应用脚本更新请求对象",description = "应用脚本更新请求对象")
public class AppScriptUpdateRequest {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value="name")
    @ApiModelProperty(value = "脚本名称")
    private String name;
    @TableField(value="enable_status")
    @ApiModelProperty(value = "开启状态")
    private String enableStatus;
    @TableField(value="describe")
    @ApiModelProperty(value = "描述")
    private String describe;
    @TableField(value="match_ip")
    @ApiModelProperty(value = "匹配ip")
    private String matchIp;
    @TableField(value="match_url")
    @ApiModelProperty(value = "匹配url")
    private String matchUrl;
    @TableField(value="req_header")
    @ApiModelProperty(value = "请求头")
    private String reqHeader;
    @TableField(value="req_method")
    @ApiModelProperty(value = "请求方法")
    private String reqMethod;
    @TableField(value="req_param")
    @ApiModelProperty(value = "请求参数")
    private String reqParam;
    @TableField(value="resp_header")
    @ApiModelProperty(value = "响应头")
    private String respHeader;
    @TableField(value="body")
    @ApiModelProperty(value = "body体")
    private String body;
    @TableField(value="style")
    @ApiModelProperty(value = "脚本类型")
    private String style;
    @TableField(value="address")
    @ApiModelProperty(value = "地址")
    private String address;
    @TableField(value="content")
    @ApiModelProperty(value = "内容")
    private String content;

    @TableField(value="is_deleted")
    private Integer isDeleted;

    @TableField(value="updated_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @TableField(value="created_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
}
