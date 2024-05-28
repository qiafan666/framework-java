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
import com.ning.web.jotato.base.model.page.MyPageResult;
/**
 * <p>
 * 告警详情表 服务类
 * </p>
 *
 * @author ning
 * @since 2024-05-28 09:20:20
 */
public interface IAlarmDetailService extends IService<AlarmDetailEntity> {

    Page<RespAlarmDetailList> list(ReqAlarmDetailList req);

    //数据联表查询
    MyPageResult<RespAlarmDetailList> linkList(ReqAlarmDetailList req);

    void create(ReqAlarmDetailCreate req);

    void update(ReqAlarmDetailUpdate req);

    void delete(List<Long> req);
}
