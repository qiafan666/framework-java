package com.ning.web.convert;

import com.ning.web.entity.AppEntity;
import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppEntityConvertor {


    AppEntityConvertor INSTANCE = Mappers.getMapper(AppEntityConvertor.class);


    List<RespAppList> appEntityToRespAppList(List<AppEntity> request);

    AppEntity appCreateRequestToAppEntity(ReqAppCreate request);

    AppEntity appUpdateRequestToAppEntity(ReqAppUpdate request);
}
