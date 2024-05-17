package com.ning.web.jotato.core.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.base.model.result.BaseBean;

import java.lang.reflect.Field;

public class BaseReq extends BaseBean {

    public BaseReq toObject(String packet) {
        if (packet != null && !packet.trim().isEmpty()) {
            JSONObject packetData = JSON.parseObject(packet);
            Field[] fields = this.getClass().getDeclaredFields();
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                packetData.remove(field.getName());
            }

            BaseReq reqEntity = (BaseReq) JSON.parseObject(packet, this.getClass());
            return reqEntity;
        } else {
            return this;
        }
    }
}
