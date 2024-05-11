package com.ning.web.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.base.common.CommonResult;
import com.ning.web.pojo.request.*;
import com.ning.web.pojo.request.app.AppDatabaseCreateRequest;
import com.ning.web.pojo.request.app.AppDatabaseListRequest;
import com.ning.web.pojo.request.app.AppDatabaseUpdateRequest;
import com.ning.web.pojo.response.*;
import com.ning.web.pojo.response.app.AppDatabaseCreateResponse;
import com.ning.web.pojo.response.app.AppDatabaseListResponse;
import com.ning.web.pojo.response.app.AppDatabaseUpdateResponse;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags = "应用")
@RestController
@RequestMapping(value = "/v1/app/database")
@AllArgsConstructor
public class AppDatabaseController {

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建应用数据源", httpMethod = "POST", notes = "创建应用数据源",response = AppDatabaseCreateResponse.class)
    public CommonResult<AppDatabaseCreateResponse> create (@RequestBody @ApiParam AppDatabaseCreateRequest appDatabaseCreateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppDatabaseCreateResponse());
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新应用数据源", httpMethod = "POST", notes = "更新应用数据源",response = AppDatabaseUpdateResponse.class)
    public CommonResult<AppDatabaseUpdateResponse> update (@RequestBody @ApiParam AppDatabaseUpdateRequest appDatabaseUpdateRequest, HttpServletRequest request) {
        return CommonResult.success(new AppDatabaseUpdateResponse());
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除应用数据源", httpMethod = "POST", notes = "删除应用数据源")
    public CommonResult<Void> delete (@RequestBody @ApiParam List<Long> appScriptIds, HttpServletRequest request) {
        return CommonResult.success(null);
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取应用数据源列表", httpMethod = "POST", notes = "获取应用数据源列表",response = AppDatabaseListResponse.class)
    public CommonResult<Page<AppDatabaseListResponse>> list (@RequestBody @ApiParam AppDatabaseListRequest appDatabaseListRequest, HttpServletRequest request) {
        return CommonResult.success(new Page<>());
    }

    @PostMapping(value = "/import")
    @ApiOperation(value = "导入应用数据源", httpMethod = "POST", notes = "导入应用列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", paramType="form", value = "上传文件", dataType="file", required = true)
    })
    public CommonResult<String> importData(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        return CommonResult.success("success");
    }

    @PostMapping(value = "/type")
    @ApiOperation(value = "获取应用数据源类型", httpMethod = "POST", notes = "获取应用数据源类型",response = List.class)
    public CommonResult<List<String>> getType (HttpServletRequest request) {
        return CommonResult.success(new ArrayList<>());
    }
}
