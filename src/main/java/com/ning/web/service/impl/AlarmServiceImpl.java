package com.ning.web.service.impl;

import com.ning.web.entity.AlarmEntity;
import com.ning.web.mapper.AlarmMapper;
import com.ning.web.service.IAlarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ning.web.pojo.req.ReqAlarmCreate;
import com.ning.web.pojo.req.ReqAlarmUpdate;
import com.ning.web.pojo.req.ReqAlarmList;
import com.ning.web.pojo.resp.RespAlarmList;
import com.ning.web.service.IAlarmService;
import com.ning.web.convert.AlarmEntityConvertor;
import com.ning.web.mapper.AlarmMapper;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.utils.NullAwareBeanUtils;
/**
 * <p>
 * 告警信息表 服务实现类
 * </p>
 *
 * @author ning
 * @since 2024-05-25 11:28:21
 */
@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, AlarmEntity> implements IAlarmService {
 
    @Override
    public Page<RespAlarmList> list(ReqAlarmList request) {

        Page<AlarmEntity> page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<AlarmEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(AlarmEntity::getCreatedTime);
        //TODO 查询条件

        Page<AlarmEntity> result = this.page(page, queryWrapper);

        List<RespAlarmList> convertList = AlarmEntityConvertor.INSTANCE.AlarmEntityToRespAlarmList(result.getRecords());
        Page<RespAlarmList> objectPage = new Page<>(request.getPageNo(), request.getPageSize(), result.getTotal());
        objectPage.setRecords(convertList);
        return objectPage;
    }

    @Override
    public void create(ReqAlarmCreate request) {

        AlarmEntity entity = new AlarmEntity();
        BeanUtils.copyProperties(request, entity);
        //TODO 校验参数
        this.save(entity);
    }

    @Override
    public void update(ReqAlarmUpdate request) {

        AlarmEntity entity = this.getById(request.getId());
        RestException.TrueThrow(entity == null, "COMMON0001");        NullAwareBeanUtils.copyProperties(request, entity);
        //TODO 参数校验
        this.updateById(entity);
    }

    @Override
    public void delete(List<Long> request) {

        request.forEach(id -> {
        AlarmEntity entity = this.getById(id);
        RestException.TrueThrow(entity == null, "COMMON0001");        entity.setIsDeleted(1);
        this.updateById(entity);
        });
    }
}
