package com.ning.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import com.ning.web.service.IAppService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 应用表 前端控制器
 * </p>
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private IAppService iAppService;


    @PostMapping(value = "/list")
    public BaseResultData<Page<RespAppList>> list(@RequestBody ReqAppList request) {
        Page<RespAppList> page =  iAppService.list(request);
        return BaseResultData.success(page);
    }

    @PostMapping(value = "/create")
    public BaseResult create(@RequestBody ReqAppCreate request) {
        iAppService.create(request);
        return BaseResultData.success();
    }

    @PostMapping(value = "/update")
    public BaseResult update(@RequestBody ReqAppUpdate request) {
        iAppService.update(request);
        return BaseResult.success();
    }

    @DeleteMapping(value = "/delete")
    public BaseResult delete(@RequestParam("ids") List<Long> request) {
        iAppService.delete(request);
        return BaseResultData.success();
    }
}
