package com.ning.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.pojo.req.ReqAlarmCreate;
import com.ning.web.pojo.req.ReqAlarmUpdate;
import com.ning.web.pojo.req.ReqAlarmList;
import com.ning.web.pojo.resp.RespAlarmList;
import com.ning.web.service.IAlarmService;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import org.springframework.web.bind.annotation.RestController;

/**
 * 告警信息管理
 *
 * @author ning
 * @since 2024-05-25 11:28:21
 */
@RestController
@RequestMapping("/api/v1/alarm")
public class AlarmController {
    @Resource
    private IAlarmService iAlarmService;


    /**
     * 列表alarm
     * @param request 查询参数
     * @return BaseResultData<Page<RespAlarmList>> 返回结果
     * @author ning
     * @since 2024-05-25 23:28:56
     */
    @PostMapping(value = "/list")
    public BaseResultData<Page<RespAlarmList>> list(@RequestBody ReqAlarmList request) {
        Page<RespAlarmList> page =  iAlarmService.list(request);
        return BaseResultData.success(page);
    }

    /**
     * 创建alarm
     * @param request 创建参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 23:28:56
     */
    @PostMapping(value = "/create")
    public BaseResult create(@RequestBody ReqAlarmCreate request) {
        iAlarmService.create(request);
        return BaseResultData.success();
    }

    /**
     * 更新alarm
     * @param request 更新参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 23:28:56
     */
    @PostMapping(value = "/update")
    public BaseResult update(@RequestBody ReqAlarmUpdate request) {
        iAlarmService.update(request);
        return BaseResult.success();
    }

    /**
     * 删除alarm
     * @param request 删除参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 23:28:56
     */
    @PostMapping(value = "/delete")
    public BaseResult delete(@RequestParam("ids") List<Long> request) {
        iAlarmService.delete(request);
        return BaseResultData.success();
    }

}
