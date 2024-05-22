package com.ning.web.jotato.core.annotion;


import java.lang.annotation.*;


// controller 参数校验注解 example: ""appStatus:string:must:unempty::32:::^(online|offline)$"
// 字段名:数据类型:是否必传:是否允许为空:default值:最大长度:min:max:正则表达式:参数
// 其中，数据类型支持 bool,datetime,date,decimal,jsonArray,jsonObject,number,string等
// string类型支持正则表达式，例如：^(online|offline)$
// 如果empty且有默认值，则默认值会作为参数值
// 前四项目为必传项目，后面根据实际情况 example "appStatus:string:must:unempty"

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageValid {
    String[] value();
}
