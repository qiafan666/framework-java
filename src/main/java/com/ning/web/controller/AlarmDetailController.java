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
 * @since 2024-05-28 09:20:20
 */
@RestController
@RequestMapping("/api/v1/alarm/detail")
public class AlarmDetailController {
    @Resource
    private IAlarmDetailService iAlarmDetailService;


    /**
     * 列表alarm.detail
     * @param req 查询参数
     * @return BaseResultData<Page<RespAlarmDetailList>> 返回结果
     * @author ning
     * @since 2024-05-28 21:20:47
     */
    @PostMapping(value = "/list")
    public BaseResultData<Page<RespAlarmDetailList>> list(@RequestBody ReqAlarmDetailList req) {
        Page<RespAlarmDetailList> page =  iAlarmDetailService.list(req);
        return BaseResultData.success(page);
    }

    /**
     * 创建alarm.detail
     * @param req 创建参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-28 21:20:47
     */
    @PostMapping(value = "/create")
    public BaseResult create(@RequestBody ReqAlarmDetailCreate req) {
        iAlarmDetailService.create(req);
        return BaseResultData.success();
    }

    /**
     * 更新alarm.detail
     * @param req 更新参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-28 21:20:47
     */
    @PostMapping(value = "/update")
    public BaseResult update(@RequestBody ReqAlarmDetailUpdate req) {
        iAlarmDetailService.update(req);
        return BaseResult.success();
    }

    /**
     * 删除alarm.detail
     * @param req 删除参数
     * @return BaseResult 返回结果
     * @author ning
     * @since 2024-05-28 21:20:47
     */
    @PostMapping(value = "/delete")
    public BaseResult delete(@RequestParam("ids") List<Long> req) {
        iAlarmDetailService.delete(req);
        return BaseResultData.success();
    }

}
