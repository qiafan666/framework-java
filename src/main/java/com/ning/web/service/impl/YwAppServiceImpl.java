package com.ning.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ning.web.entity.YwAppEntity;
import com.ning.web.mapper.YwAppEntityMapper;
import com.ning.web.pojo.request.app.AppCreateRequest;
import com.ning.web.pojo.response.app.AppCreateResponse;
import com.ning.web.service.IYwAppService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;


@Slf4j
@Service
@AllArgsConstructor
public class YwAppServiceImpl extends ServiceImpl<YwAppEntityMapper, YwAppEntity> implements IYwAppService {

    private final YwAppEntityMapper ywAppEntityMapper;

    @Override
    public AppCreateResponse appCreate(AppCreateRequest appCreateRequest){
        List<YwAppEntity> ywAppEntities = ywAppEntityMapper.selectAll();
        return  new AppCreateResponse();
    }


}
