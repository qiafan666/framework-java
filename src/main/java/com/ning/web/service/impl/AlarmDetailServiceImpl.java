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
import com.ning.web.jotato.base.model.page.MyPage;
import com.ning.web.jotato.base.model.page.MyPageResult;
/**
 * <p>
 * 告警详情表 服务实现类
 * </p>
 *
 * @author ning
 * @since 2024-05-28 09:20:20
 */
@Service
public class AlarmDetailServiceImpl extends ServiceImpl<AlarmDetailMapper, AlarmDetailEntity> implements IAlarmDetailService {
 
    @Override
    public Page<RespAlarmDetailList> list(ReqAlarmDetailList req) {

        Page<AlarmDetailEntity> page = new Page<>(req.getPageNo(), req.getPageSize());
        LambdaQueryWrapper<AlarmDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(AlarmDetailEntity::getCreatedTime);
        //TODO 查询条件

        Page<AlarmDetailEntity> result = this.page(page, queryWrapper);

        List<RespAlarmDetailList> convertList = AlarmDetailEntityConvertor.INSTANCE.AlarmDetailEntityToRespAlarmDetailList(result.getRecords());
        Page<RespAlarmDetailList> objectPage = new Page<>(req.getPageNo(), req.getPageSize(), result.getTotal());
        objectPage.setRecords(convertList);
        return objectPage;
    }

    //数据联表查询
    @Override
    public MyPageResult<RespAlarmDetailList> linkList(ReqAlarmDetailList req) {
//        Page<RespAlarmDetailList> page = new Page<>(req.getPageNo(), req.getPageSize(),true);
//        IPage<RespAlarmDetailList> iPage = mapper.queryPage(page, req);
//        List<RespAlarmDetailList> list = iPage.getRecords();
//
//        MyPageResult<RespAlarmDetailList> myPageResult = new MyPageResult<>();
//        myPageResult.setList(list);
//        MyPage myPage = new MyPage();
//        myPage.setPageNo(req.getPageNo());
//        myPage.setPageSize(req.getPageSize());
//        myPage.setTotalRecord(iPage.getTotal());
//        myPageResult.setPage(myPage);
//        return myPageResult;
        return null;    }
    @Override
    public void create(ReqAlarmDetailCreate req) {

        AlarmDetailEntity entity = new AlarmDetailEntity();
        BeanUtils.copyProperties(req, entity);
        //TODO 校验参数
        this.save(entity);
    }

    @Override
    public void update(ReqAlarmDetailUpdate req) {

        AlarmDetailEntity entity = this.getById(req.getId());
        RestException.TrueThrow(entity == null, "COMMON0001");
        NullAwareBeanUtils.copyProperties(req, entity);
        //TODO 参数校验
        this.updateById(entity);
    }

    @Override
    public void delete(List<Long> req) {

        req.forEach(id -> {
        AlarmDetailEntity entity = this.getById(id);
        RestException.TrueThrow(entity == null, "COMMON0001");
        entity.setIsDeleted(1);
        this.updateById(entity);
        });
    }
}
