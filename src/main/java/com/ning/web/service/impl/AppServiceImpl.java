package com.ning.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.convert.AppEntityConvertor;
import com.ning.web.entity.AppEntity;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.mapper.AppMapper;

import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import com.ning.web.service.IAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 应用表 服务实现类
 * </p>
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, AppEntity> implements IAppService {

    @Override
    public Page<RespAppList> list(ReqAppList request) {
        Page<AppEntity> page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<AppEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppEntity::getIsDeleted, 0);
        Page<AppEntity> appEntityPage = this.page(page, queryWrapper);

        //TODO 条件校验
        List<RespAppList> appListResponseList = AppEntityConvertor.INSTANCE.appEntityToRespAppList(appEntityPage.getRecords());
        Page<RespAppList> objectPage = new Page<>(request.getPageNo(), request.getPageSize(), appEntityPage.getTotal());
        objectPage.setRecords(appListResponseList);
        return  objectPage;
    }

    @Override
    public void create(ReqAppCreate request) {
        AppEntity appEntity = AppEntityConvertor.INSTANCE.appCreateRequestToAppEntity(request);
        this.save(appEntity);
    }

    @Override
    public void update(ReqAppUpdate request) {
        AppEntity appEntity = this.getById(request.getAppId());
        if(ObjectUtil.isNull(appEntity)){
            throw new RestException("AE0001");
        }
        BeanUtils.copyProperties(appEntity,request);

        //TODO：条件校验
        this.updateById(appEntity);
    }

    @Override
    public void delete(List<Long> request) {
        request.forEach(appId -> {
            AppEntity appEntity = this.getById(appId);
            if(appEntity == null){
                throw new RestException("AE0001");
            }
            appEntity.setIsDeleted(1);
            this.updateById(appEntity);
        });
    }
}
