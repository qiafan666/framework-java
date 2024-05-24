package com.ning.web.service;

import com.ning.web.entity.AlarmDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ning.web.pojo.req.ReqAlarmDetailCreate;
import com.ning.web.pojo.req.ReqAlarmDetailUpdate;
import com.ning.web.pojo.req.ReqAlarmDetailList;
import com.ning.web.pojo.resp.RespAlarmDetailList;
import com.ning.web.entity.AlarmDetailEntity;
/**
 * <p>
 * 告警详情表 服务类
 * </p>
 *
 * @author ning
 * @since 2024-05-25 01:22:33
 */
public interface IAlarmDetailService extends IService<AlarmDetailEntity> {

    Page<RespAlarmDetailList> list(ReqAlarmDetailList request);

    void create(ReqAlarmDetailCreate request);

    void update(ReqAlarmDetailUpdate request);

    void delete(List<Long> request);
}
