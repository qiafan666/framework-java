package com.ning.web.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.convert.AppEntityConvertor;
import com.ning.web.entity.AppEntity;
import com.ning.web.jotato.base.model.page.MyPage;
import com.ning.web.jotato.base.model.page.MyPageResult;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @Resource
    private AppMapper appMapper;

    @Override
    public Page<RespAppList> list(ReqAppList req) {
        Page<AppEntity> page = new Page<>(req.getPageNo(), req.getPageSize());
        LambdaQueryWrapper<AppEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppEntity::getIsDeleted, 0);
        Page<AppEntity> appEntityPage = this.page(page, queryWrapper);

        //TODO 条件校验
        List<RespAppList> appListResponseList = AppEntityConvertor.INSTANCE.appEntityToRespAppList(appEntityPage.getRecords());
        Page<RespAppList> objectPage = new Page<>(req.getPageNo(), req.getPageSize(), appEntityPage.getTotal());
        objectPage.setRecords(appListResponseList);
        return  objectPage;
    }

    @Override
    public MyPageResult<RespAppList> linkList(ReqAppList req) {
        //=====================下面是联查的写法=======================
        Page<RespAppList> page = new Page<>(req.getPageNo(), req.getPageSize(),true);
        IPage<RespAppList> iPage = appMapper.queryPage(page, req);
        List<RespAppList> list = iPage.getRecords();

        MyPageResult<RespAppList> myPageResult = new MyPageResult<>();
        myPageResult.setList(list);
        MyPage myPage = new MyPage();
        myPage.setPageNo(req.getPageNo());
        myPage.setPageSize(req.getPageSize());
        myPage.setTotalRecord(iPage.getTotal());
        myPageResult.setPage(myPage);
        return myPageResult;
    }

    @Override
    public void create(ReqAppCreate request) {
        AppEntity entity = new AppEntity();
        BeanUtils.copyProperties(request, entity);
        //TODO 校验参数
        this.save(entity);
    }

    @Override
    public void update(ReqAppUpdate req) {
        AppEntity appEntity = this.getById(req.getAppId());
        RestException.TrueThrow(appEntity == null, "COMMON001");
        NullAwareBeanUtils.copyProperties(appEntity,req);

        //TODO：条件校验
        this.updateById(appEntity);
    }

    @Override
    public void delete(List<Long> req) {
        req.forEach(appId -> {
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
