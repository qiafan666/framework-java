package com.ning.web.mapper;

import com.ning.web.entity.AlarmEntity;
import com.ning.web.jotato.base.support.WebBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 告警信息表 Mapper 接口
 * </p>
 *
 * @author ning
 * @since 2024-05-25 11:28:21
 */
@Mapper
public interface AlarmMapper extends WebBaseMapper<AlarmEntity> {

}
