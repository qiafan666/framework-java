package com.ning.web.jotato.core.support.logger;


import com.ning.web.jotato.core.support.logger.printer.JsonLogPrinter;
import com.ning.web.jotato.core.support.logger.printer.NoneLogPrinter;
import com.ning.web.jotato.core.support.logger.printer.TextLogPrinter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAutoConfig {


    public LogAutoConfig() {
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "logging.pattern",
            name = {"output-type"},
            havingValue = "text",
            matchIfMissing = true
    )
    public LogPrinter textLogPrinter() {
        return new TextLogPrinter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "logging.pattern",
            name = {"output-type"},
            havingValue = "json",
            matchIfMissing = false
    )
    public LogPrinter jsonLogPrinter() {
        return new JsonLogPrinter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "logging.pattern",
            name = {"output-type"},
            havingValue = "none",
            matchIfMissing = false
    )
    public LogPrinter noneLogPrinter() {
        return new NoneLogPrinter();
    }
}
