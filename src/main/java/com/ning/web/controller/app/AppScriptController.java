package com.ning.web.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.base.common.CommonResult;
import com.ning.web.pojo.request.*;
import com.ning.web.pojo.request.app.AppScriptCreateRequest;
import com.ning.web.pojo.request.app.AppScriptListRequest;
import com.ning.web.pojo.request.app.AppScriptUpdateRequest;
import com.ning.web.pojo.response.*;
import com.ning.web.pojo.response.app.AppScriptCreateResponse;
import com.ning.web.pojo.response.app.AppScriptListResponse;
import com.ning.web.pojo.response.app.AppScriptUpdateResponse;
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
@RequestMapping(value = "/v1/app/script")
@AllArgsConstructor
public class AppScriptController {

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建应用脚本", httpMethod = "POST", notes = "创建应用脚本",response = AppScriptCreateResponse.class)
    public CommonResult<AppScriptCreateResponse> create (@RequestBody @ApiParam AppScriptCreateRequest appScriptCreateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppScriptCreateResponse());
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新应用脚本", httpMethod = "POST", notes = "更新应用脚本",response = AppScriptUpdateResponse.class)
    public CommonResult<AppScriptUpdateResponse> update (@RequestBody @ApiParam AppScriptUpdateRequest appScriptUpdateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppScriptUpdateResponse());
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除应用脚本", httpMethod = "POST", notes = "删除应用脚本")
    public CommonResult<Void> delete (@RequestBody @ApiParam List<Long> appScriptIds, HttpServletRequest request) {
        return CommonResult.success(null);
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取应用脚本列表", httpMethod = "POST", notes = "获取应用脚本列表",response = AppScriptListResponse.class)
    public CommonResult<Page<AppScriptListResponse>> list (@RequestBody @ApiParam AppScriptListRequest appScriptListRequest, HttpServletRequest request) {
        return CommonResult.success(new Page<>());
    }
}
