package com.ning.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.entity.AppEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 应用表 服务类
 * </p>
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
public interface IAppService extends IService<AppEntity> {

    Page<RespAppList> list(ReqAppList request);

    void create(ReqAppCreate request);

    void update(ReqAppUpdate request);

    void delete(List<Long> request);

    void processFile(MultipartFile multipartFile);
}
