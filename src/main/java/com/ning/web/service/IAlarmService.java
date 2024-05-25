package com.ning.web.service;

import com.ning.web.entity.AlarmEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ning.web.pojo.req.ReqAlarmCreate;
import com.ning.web.pojo.req.ReqAlarmUpdate;
import com.ning.web.pojo.req.ReqAlarmList;
import com.ning.web.pojo.resp.RespAlarmList;
import com.ning.web.entity.AlarmEntity;
/**
 * <p>
 * 告警信息表 服务类
 * </p>
 *
 * @author ning
 * @since 2024-05-25 11:28:21
 */
public interface IAlarmService extends IService<AlarmEntity> {

    Page<RespAlarmList> list(ReqAlarmList request);

    void create(ReqAlarmCreate request);

    void update(ReqAlarmUpdate request);

    void delete(List<Long> request);
}
