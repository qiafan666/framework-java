package com.ning.web.pojo.template;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AppImportDTO implements Serializable {

    private static final long serialVersionUID = -265741933689599525L;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("邮箱")
    private String mail;

    @ExcelProperty("账号")
    private String loginName;
}
