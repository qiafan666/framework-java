package com.ning.web.controller.app;

import com.ning.web.base.common.CommonResult;
import com.ning.web.pojo.request.app.AppApiCreateRequest;
import com.ning.web.pojo.request.app.AppApiListRequest;
import com.ning.web.pojo.request.app.AppApiUpdateRequest;
import com.ning.web.pojo.response.app.AppApiCreateResponse;
import com.ning.web.pojo.response.app.AppApiListResponse;
import com.ning.web.pojo.response.app.AppApiUpdateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api(tags = "应用")
@RestController
@RequestMapping(value = "/v1/app/api")
@AllArgsConstructor
public class AppApiController {

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建应用API", httpMethod = "POST", notes = "创建应用API",response = AppApiCreateResponse.class)
    public CommonResult<AppApiCreateResponse> create (@RequestBody @ApiParam AppApiCreateRequest appApiCreateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppApiCreateResponse());
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新应用API", httpMethod = "POST", notes = "更新应用API",response = AppApiUpdateResponse.class)
    public CommonResult<AppApiUpdateResponse> update (@RequestBody @ApiParam AppApiUpdateRequest appApiUpdateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppApiUpdateResponse());
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除应用API", httpMethod = "POST", notes = "删除应用API")
    public CommonResult<Void> delete (@RequestBody @ApiParam List<Long> appApiIds, HttpServletRequest request) {
        return CommonResult.success(null);
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取应用API列表", httpMethod = "POST", notes = "获取应用API列表",response = AppApiListResponse.class)
    public CommonResult<AppApiListResponse> list (@RequestBody @ApiParam AppApiListRequest appApiListRequest, HttpServletRequest request) {
        return CommonResult.success(new AppApiListResponse());
    }
}
