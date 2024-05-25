package com.ning.web.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.convert.AppEntityConvertor;
import com.ning.web.entity.AppEntity;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.common.utils.NullAwareBeanUtils;
import com.ning.web.listenner.AppImportListenner;
import com.ning.web.mapper.AppMapper;

import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import com.ning.web.pojo.template.AppImportDTO;
import com.ning.web.service.IAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        RestException.TrueThrow(appEntity == null, "COMMON001");
        NullAwareBeanUtils.copyProperties(appEntity,request);

        //TODO：条件校验
        this.updateById(appEntity);
    }

    @Override
    public void delete(List<Long> request) {
        request.forEach(appId -> {
            AppEntity appEntity = this.getById(appId);
            RestException.TrueThrow(appEntity == null, "COMMON001");
            appEntity.setIsDeleted(1);
            this.updateById(appEntity);
        });
    }

    @Transactional
    @Override
    public void processFile(MultipartFile multipartFile) {
        //处理文件导入
        RestException.TrueThrow(multipartFile == null, "FE00002");

        String originalFilename = multipartFile.getOriginalFilename();
        RestException.TrueThrow(originalFilename == null ||
                !originalFilename.endsWith("xlsx"), "FE00001");

        List<AppImportDTO> list = new LinkedList<>();
        try {
            EasyExcel.read(multipartFile.getInputStream(),AppImportDTO.class,new AppImportListenner(list)).sheet().doRead();
        } catch (IOException e) {
            throw new RestException("FE00004");
        }

        RestException.TrueThrow(CollectionUtil.isEmpty(list), "FE00005");

        ArrayList<AppEntity> appEntities = new ArrayList<>();
        for (AppImportDTO result : list) {
            //TODO 业务逻辑处理


            AppEntity appEntity = new AppEntity();
            BeanUtils.copyProperties(result, appEntity);

            //新增普通用户
            appEntities.add(appEntity);
        }
        this.saveBatch(appEntities,500);
    }
}
