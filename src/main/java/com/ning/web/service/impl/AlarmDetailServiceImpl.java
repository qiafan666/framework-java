package com.ning.web.service.impl;

import com.ning.web.entity.AlarmDetailEntity;
import com.ning.web.mapper.AlarmDetailMapper;
import com.ning.web.service.IAlarmDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ning.web.pojo.req.ReqAlarmDetailCreate;
import com.ning.web.pojo.req.ReqAlarmDetailUpdate;
import com.ning.web.pojo.req.ReqAlarmDetailList;
import com.ning.web.pojo.resp.RespAlarmDetailList;
import com.ning.web.service.IAlarmDetailService;
import com.ning.web.convert.AlarmDetailEntityConvertor;
import com.ning.web.mapper.AlarmDetailMapper;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.utils.NullAwareBeanUtils;
/**
 * <p>
 * 告警详情表 服务实现类
 * </p>
 *
 * @author ning
 * @since 2024-05-25 01:22:33
 */
@Service
public class AlarmDetailServiceImpl extends ServiceImpl<AlarmDetailMapper, AlarmDetailEntity> implements IAlarmDetailService {
 
    @Override
    public Page<RespAlarmDetailList> list(ReqAlarmDetailList request) {

        Page<AlarmDetailEntity> page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<AlarmDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(AlarmDetailEntity::getCreatedTime);
        //TODO 查询条件

        Page<AlarmDetailEntity> result = this.page(page, queryWrapper);

        List<RespAlarmDetailList> convertList = AlarmDetailEntityConvertor.INSTANCE.AlarmDetailEntityToRespAlarmDetailList(result.getRecords());
        Page<RespAlarmDetailList> objectPage = new Page<>(request.getPageNo(), request.getPageSize(), result.getTotal());
        objectPage.setRecords(convertList);
        return  objectPage;
    }

    @Override
    public void create(ReqAlarmDetailCreate request) {

        AlarmDetailEntity entity = new AlarmDetailEntity();
        BeanUtils.copyProperties(request, entity);
        //TODO 校验参数
        this.save(entity);
    }

    @Override
    public void update(ReqAlarmDetailUpdate request) {

        AlarmDetailEntity entity = this.getById(request.getId());
        if (entity == null) {
            throw new RestException("COMMON0001");
        }
        NullAwareBeanUtils.copyProperties(request, entity);
        //TODO 参数校验
        this.updateById(entity);
    }

    @Override
    public void delete(List<Long> request) {

        request.forEach(id -> {
        AlarmDetailEntity entity = this.getById(id);
        if (entity == null) {
            throw new RestException("COMMON0001");
            }
        entity.setIsDeleted(1);
        this.updateById(entity);
        });
    }
}
