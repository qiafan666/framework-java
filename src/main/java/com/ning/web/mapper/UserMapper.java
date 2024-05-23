package com.ning.web.mapper;

import com.ning.web.entity.UserEntity;
import com.ning.web.jotato.base.support.WebBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ning
 * @since 2024-05-23 05:30:43
 */
@Mapper
public interface UserMapper extends WebBaseMapper<UserEntity> {

}
