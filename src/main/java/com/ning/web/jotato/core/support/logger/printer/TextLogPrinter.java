package com.ning.web.jotato.core.support.logger.printer;

import com.ning.web.jotato.core.support.logger.LogPrinter;
import com.ning.web.jotato.core.web.context.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

public class TextLogPrinter implements LogPrinter {
    private static final Logger logger = LoggerFactory.getLogger(TextLogPrinter.class);

    private static Environment env;

    public TextLogPrinter() {
    }

    @Autowired(
            required = false
    )
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    public static Environment getEnvironment() {
        return env;
    }


    @Override
    public void printIn(ActionContext context) {
        StringBuffer logbuf = new StringBuffer();
        logbuf.append("\r\n--------------------------------------request--------------------------------------------\r\n");
        if (context.getPathInfo() != null) {
            logbuf.append("[pathInfo]    ").append(context.getPathInfo()).append("\r\n");
        }

        if (context.getContentType() != null) {
            logbuf.append("[contentType] ").append(context.getContentType()).append("\r\n");
        }

        if (context.getHttpMethod() != null) {
            logbuf.append("[httpMethod]  ").append(context.getHttpMethod()).append("\r\n");
        }

        if (context.getRequestId() != null) {
            logbuf.append("[requestId]   ").append(context.getRequestId()).append("\r\n");
        }

        if (context.getInPacket() != null) {
            String packetStr = context.getInPacket();
            List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains("prod") && packetStr.length() > 5000) {
                packetStr = packetStr.substring(0, 5000) + "...";
            }

            logbuf.append("[packet]      ").append(packetStr).append("\r\n");
        }

        logbuf.append("-----------------------------------------------------------------------------------------\r\n");
        logger.info(logbuf.toString());
    }

//    @Override
//    public void printIn(BizLogEntity bizLogEntity) {
//        StringBuffer logbuf = new StringBuffer();
//        logbuf.append("\r\n--------------------------------------request--------------------------------------------\r\n");
//
//
//        if (bizLogEntity.getLogType() != null) {
//            logbuf.append("[logType]    ").append(bizLogEntity.getLogType()).append("\r\n");
//        }
//        if (bizLogEntity.getService() != null) {
//            logbuf.append("[service]    ").append(bizLogEntity.getService()).append("\r\n");
//        }
//        if (bizLogEntity.getServiceApi() != null) {
//            logbuf.append("[serviceApi  ").append(bizLogEntity.getServiceApi()).append("\r\n");
//        }
//
//        if (bizLogEntity.getInputParam() != null) {
//            String packetStr = JSON.toJSONString(bizLogEntity.getInputParam());
//            List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
//            if (activeProfiles.contains("prod") && packetStr.length() > 5000) {
//                packetStr = packetStr.substring(0, 5000) + "...";
//            }
//
//            logbuf.append("[packet]      ").append(packetStr).append("\r\n");
//        }
//
//        logbuf.append("-----------------------------------------------------------------------------------------\r\n");
//        logger.info(logbuf.toString());
//    }

    @Override
    public void printOut(ActionContext context) {
        StringBuffer logbuf = new StringBuffer();
        logbuf.append("\r\n--------------------------------------response-------------------------------------------\r\n");
        if (context.getPathInfo() != null) {
            logbuf.append("[pathInfo]    ").append(context.getPathInfo()).append("\r\n");
        }

        if (context.getContentType() != null) {
            logbuf.append("[contentType] ").append(context.getContentType()).append("\r\n");
        }

        if (context.getHttpMethod() != null) {
            logbuf.append("[httpMethod]  ").append(context.getHttpMethod()).append("\r\n");
        }

        if (context.getRequestId() != null) {
            logbuf.append("[requestId]   ").append(context.getRequestId()).append("\r\n");
        }

        if (context.getStartTime() != 0L) {
            logbuf.append("[totalTime]   ").append(System.currentTimeMillis() - context.getStartTime()).append("ms").append("\r\n");
        }

        if (context.getOutPacket() != null) {
            String packetStr = context.getOutPacket();
            List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains("prod") && packetStr.length() > 5000) {
                packetStr = packetStr.substring(0, 5000) + "...";
            }

            logbuf.append("[packet]      ").append(packetStr).append("\r\n");
        }

        logbuf.append("-----------------------------------------------------------------------------------------\r\n");
        logger.info(logbuf.toString());
    }


}
