package com.ning.web.jotato.common.constants;

import java.nio.charset.Charset;

public interface SysConstant {
        String DEFAULT_ENCODING = "UTF-8";
        Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        String PSN = "Psn";
        String TIME_ZONE = "Accept-Timezone";
        String I18N_LANGUAGE_PARAM_NAME = "Accept-Language";
        int HTTP_DEFAULT_TIMEOUT = 30000;
        int LOG_MAX_SIZE = 5000;
        String ENV_DEV = "dev";
        String ENV_TEST = "test";
        String ENV_PROD = "prod";

        String AUTO_LOGIN = "autoLogin";
    }
