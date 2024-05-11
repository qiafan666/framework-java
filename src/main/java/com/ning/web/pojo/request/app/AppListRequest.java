package com.ning.web.pojo.request.app;

import com.ning.web.pojo.common.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "应用列表请求参数",description = "应用列表请求参数")
public class AppListRequest extends BasePageReq {

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用服务名称")
    private String appServiceName;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "主机")
    private String host;

    @ApiModelProperty(value = "端口")
    private String port;

    @ApiModelProperty(value = "webroot")
    private String webroot;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "http状态")
    private String httpStatus;

    @ApiModelProperty(value = "分类状态")
    private String classifyStatus;

    @ApiModelProperty(value = "数据库日志状态")
    private String databaselogStatus;

    @ApiModelProperty(value = "API日志状态")
    private String apilogStatus;
}
