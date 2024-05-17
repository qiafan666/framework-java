package com.ning.web.jotato.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "mask")
public class MaskConfig {

    private Map<String, MaskRule> rules;

    @Data
    public static class MaskRule {

        private Boolean valid;

        private String key;

        private String regex;

        private String replacement;
    }
}
