
package com.ning.web.convert;

import com.ning.web.entity.AlarmDetailEntity;
import com.ning.web.pojo.resp.RespAlarmDetailList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;

/**
* AlarmDetail类型转换器
* @author ning
* @since 2024-05-25 01:22:42
*/
@Mapper(componentModel = "spring")
public interface AlarmDetailEntityConvertor {


    AlarmDetailEntityConvertor INSTANCE = Mappers.getMapper(AlarmDetailEntityConvertor.class);


    List<RespAlarmDetailList> AlarmDetailEntityToRespAlarmDetailList(List<AlarmDetailEntity> records);
}
