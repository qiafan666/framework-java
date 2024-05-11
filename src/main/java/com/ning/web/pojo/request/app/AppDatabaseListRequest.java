package com.ning.web.pojo.request.app;

import com.ning.web.pojo.common.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "应用数据库列表请求参数",description = "应用数据库列表请求参数")
public class AppDatabaseListRequest extends BasePageReq {

    @ApiModelProperty(value = "数据源分组ID")
    private String group_id;

    @ApiModelProperty(value = "数据库类型")
    private String database_type;
}
