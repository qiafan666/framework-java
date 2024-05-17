package com.ning.web.mapper;

import com.ning.web.entity.AppEntity;
import com.ning.web.jotato.base.support.WebBaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
