package com.ning.web.service;

import com.ning.web.pojo.request.app.AppCreateRequest;
import com.ning.web.pojo.response.app.AppCreateResponse;

public interface IYwAppService {

    AppCreateResponse appCreate(AppCreateRequest appCreateRequest);
}