package com.ning.web.pojo.request.app;

import com.ning.web.pojo.common.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "应用API列表请求参数",description = "应用API列表请求参数")
public class AppApiListRequest extends BasePageReq {

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "服务名称")
    private String appServiceName;

    @ApiModelProperty(value = "host")
    private String host;

    @ApiModelProperty(value = "端口")
    private String port;

    @ApiModelProperty(value = "webroot")
    private String webroot;
}
