package com.ning.web.controller.app;
import com.ning.web.base.common.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.base.constants.TokenConsts;
import com.ning.web.pojo.request.app.AppCreateRequest;
import com.ning.web.pojo.request.app.AppListRequest;
import com.ning.web.pojo.request.app.AppUpdateRequest;
import com.ning.web.pojo.response.app.AppCreateResponse;
import com.ning.web.pojo.response.app.AppListResponse;
import com.ning.web.pojo.response.app.AppUpdateResponse;
import com.ning.web.service.IYwAppService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api(tags = "应用")
@RestController
@RequestMapping(value = "/v1/app")
@AllArgsConstructor
public class AppController {

    private final IYwAppService iYwAppService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建应用", httpMethod = "POST", notes = "创建应用",response = AppCreateResponse.class)
    public CommonResult<AppCreateResponse> create(@RequestBody @ApiParam AppCreateRequest createRequest, HttpServletRequest request) {
        String userId = Objects.toString(request.getAttribute(TokenConsts.USER_ID),"");
        AppCreateResponse appCreateResponse = iYwAppService.appCreate(createRequest);
        return CommonResult.success(appCreateResponse);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新应用", httpMethod = "POST", notes = "更新应用",response = AppUpdateResponse.class)
    public CommonResult<AppUpdateResponse> update(@RequestBody @ApiParam AppUpdateRequest updateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppUpdateResponse());
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除应用", httpMethod = "POST", notes = "删除应用")
    public CommonResult<String> delete(@RequestBody @ApiParam List<Long> appIds, HttpServletRequest request) {
        return CommonResult.success("success");
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取应用列表", httpMethod = "POST", notes = "获取应用列表")
    public CommonResult<Page<AppListResponse>> list(@RequestBody @ApiParam AppListRequest appListRequest, HttpServletRequest request) {
        return CommonResult.success(new Page<>());
    }

    @PostMapping(value = "/import")
    @ApiOperation(value = "导入应用列表", httpMethod = "POST", notes = "导入应用列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", paramType="form", value = "上传文件", dataType="file", required = true)
    })
    public CommonResult<String> importData(@RequestPart("file") MultipartFile file,HttpServletRequest request) {
        return CommonResult.success("success");
    }

    @PostMapping(value = "/export")
    @ApiOperation(value = "导出应用列表", httpMethod = "POST", notes = "导出应用列表")
    public CommonResult<String> export(@RequestBody @ApiParam List<Long> appIds, HttpServletRequest request) {
        return CommonResult.success("success");
    }

}
