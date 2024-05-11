package com.ning.web.base.utils;


import com.ning.web.base.bean.BizException;
import com.ning.web.base.bean.WebResultCode;

/**
 * @program: test-parent
 * 断言工具类
 * @author ning
 * @create: 2022-03-17 21:11
 * @description: 判断条件是否成立，不成立则抛出异常
 * @version: 5.0
 * @Copyright: (c) by Systex Group(China)Ltd. SCM
 */
public class Assert {

    /*
     * create by:  zhouxq
     * description: 是否为空
     * create time: 2022/3/17/017 21:17
     * @params [bool, message]
     * @return void
     */
    public static void isTrue(boolean bool, WebResultCode webResultCode){
        if(bool){
            throw new BizException(webResultCode);
        }
    }
}
