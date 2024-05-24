package com.ning.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.pojo.req.ReqAlarmDetailCreate;
import com.ning.web.pojo.req.ReqAlarmDetailUpdate;
import com.ning.web.pojo.req.ReqAlarmDetailList;
import com.ning.web.pojo.resp.RespAlarmDetailList;
import com.ning.web.service.IAlarmDetailService;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import org.springframework.web.bind.annotation.RestController;

/**
 * 告警详情管理
 *
 * @author ning
 * @since 2024-05-25 01:22:33
 */
@RestController
@RequestMapping("/alarm/detail")
public class AlarmDetailController {
    @Resource
    private IAlarmDetailService iAlarmDetailService;


    /**
     * 列表查询AlarmDetail
     * @param request 查询参数
     * @return BaseResultData<Page<RespAlarmDetailList>> 返回结果
     * @author ning
     * @since 2024-05-25 01:22:42
     */
    @PostMapping(value = "/list")
    public BaseResultData<Page<RespAlarmDetailList>> list(@RequestBody ReqAlarmDetailList request) {
        Page<RespAlarmDetailList> page =  iAlarmDetailService.list(request);
        return BaseResultData.success(page);
    }

    /**
     * 创建AlarmDetail
     * @param request 创建参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 01:22:42
     */
    @PostMapping(value = "/create")
    public BaseResult create(@RequestBody ReqAlarmDetailCreate request) {
        iAlarmDetailService.create(request);
        return BaseResultData.success();
    }

    /**
     * 更新AlarmDetail
     * @param request 更新参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 01:22:42
     */
    @PostMapping(value = "/update")
    public BaseResult update(@RequestBody ReqAlarmDetailUpdate request) {
        iAlarmDetailService.update(request);
        return BaseResult.success();
    }

    /**
     * 删除AlarmDetail
     * @param request 删除参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-25 01:22:42
     */
    @PostMapping(value = "/delete")
    public BaseResult delete(@RequestParam("ids") List<Long> request) {
        iAlarmDetailService.delete(request);
        return BaseResultData.success();
    }

}
