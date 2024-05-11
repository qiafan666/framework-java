package com.ning.web.pojo.request.app;

import com.ning.web.pojo.common.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "应用脚本列表请求参数",description = "应用脚本列表请求参数")
public class AppScriptListRequest extends BasePageReq {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "url")
    private String url;
}
