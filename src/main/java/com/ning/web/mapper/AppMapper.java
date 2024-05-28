package com.ning.web.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.entity.AppEntity;
import com.ning.web.jotato.base.support.WebBaseMapper;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.resp.RespAppList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 应用表 Mapper 接口
 * </p>
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
@Mapper
public interface AppMapper extends WebBaseMapper<AppEntity> {

    IPage<RespAppList> queryPage(@Param("page") Page<RespAppList> page,@Param("req") ReqAppList req);
}
