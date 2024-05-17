package com.ning.web.jotato.common.constants;

public class Consts {

    public static final String RS = "1234567890123456";

    /**
     * 经计算，6位随机数种子
     */
    public static final Long TENANT_DOMAIN_SEED = 1291467969L;
    public interface SYS_USER_TYPE {
        String CLIENT_USER = "CLIENT_USER";
        String ADMIN_USER = "ADMIN_USER";
    }

    public interface CACHE_KEY {

        /**
         * redis key 租户
         */
        String CLIENT_USER_TOKE_KEY = "clientUserToken:";

        /**
         * redis key 运营
         */
        String ADMIN_USER_TOKE_KEY = "adminUserToken:";
    }

    public interface DATE_PATTERN {

        String YYYYMMDDHH = "yyyyMMddHH";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    }




    public interface FILE {

        String PIC_PREFIX = "pic_";
    }

    public interface LOGIN_NAME {

        String SPLIT_KEY_CHART = "#";

        String PATTERN = "^([a-zA-Z0-9_-]+)#([a-zA-Z0-9]{1,6})$";

        String PATTERN_NO_DOMAIN = "^([a-zA-Z0-9_-]+)$";

    }



    public interface INIT_STEP {
        /**
         * 设置管理员
         */
        String ADMIN_SET = "ADMIN_SET";
        /**
         * 设置OSS
         */
        String OSS_SET = "OSS_SET";
        /**
         * 设置公司
         */
        String COMPANY_SET = "COMPANY_SET";
        /**
         * 设置部门
         */
        String DEPART_SET = "DEPART_SET";
        String COMPLETE = "COMPLETE";
    }
}
