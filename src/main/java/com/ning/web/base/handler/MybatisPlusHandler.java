package com.ning.web.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: test-parent
 * 创建人自动填充
 * @author ning
 * @create: 2022-03-12 21:27
 * @version: 5.0
 * @Copyright: (c) by Systex Group(China)Ltd. SCM
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    @Autowired
    private HttpServletRequest request;

    private final String CREATE_TIME = "created_time";
    private final String UPDATE_TIME = "updated_time";

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setInsertFieldValByName(CREATE_TIME,date,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        this.setUpdateFieldValByName(UPDATE_TIME,date,metaObject);
    }
}
