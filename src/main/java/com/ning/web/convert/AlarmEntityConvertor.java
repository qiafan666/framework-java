
package com.ning.web.convert;

import com.ning.web.entity.AlarmEntity;
import com.ning.web.pojo.resp.RespAlarmList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;

/**
* Alarm类型转换器
* @author ning
* @since 2024-05-25 23:28:56
*/
@Mapper(componentModel = "spring")
public interface AlarmEntityConvertor {


    AlarmEntityConvertor INSTANCE = Mappers.getMapper(AlarmEntityConvertor.class);


    List<RespAlarmList> AlarmEntityToRespAlarmList(List<AlarmEntity> records);
}
