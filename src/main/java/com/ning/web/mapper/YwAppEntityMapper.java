package com.ning.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ning.web.entity.YwAppEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YwAppEntityMapper extends BaseMapper<YwAppEntity> {
    int deleteByPrimaryKey(Long id);

    int insert(YwAppEntity record);

    YwAppEntity selectByPrimaryKey(Long id);

    List<YwAppEntity> selectAll();

    int updateByPrimaryKey(YwAppEntity record);
}