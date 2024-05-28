package com.ning.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.entity.AppEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ning.web.jotato.base.model.page.MyPageResult;
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

    Page<RespAppList> list(ReqAppList req);

    MyPageResult<RespAppList> linkList(ReqAppList req);

    void create(ReqAppCreate req);

    void update(ReqAppUpdate req);

    void delete(List<Long> req);

    void processFile(MultipartFile multipartFile);
}
