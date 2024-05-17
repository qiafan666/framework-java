package com.ning.web.jotato.common.i18n;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Conditional({I18NAutoConfig.ResourceAntPathCondition.class})
public class I18NAutoConfig extends MessageSourceAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(I18NAutoConfig.class);

    public I18NAutoConfig() {
    }

    @Bean
    @Primary
    public MessageSource messageSource(MessageSourceProperties properties) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        if (StringUtils.hasText(properties.getBasename())) {
            messageSource.setBasenames(this.getRealBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename()))));
        }

        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }

        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }

        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }

    private String[] getRealBasenames(String[] basenames) {
        Set<String> basenameSet = new HashSet();
        String[] basenameArr = basenames;
        int var4 = basenames.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String basename = basenameArr[var5];
            String target = basename.replace('.', '/');
            String rootDirPath = this.determineRootDir(target);
            Resource[] resources = new Resource[0];

            try {
                resources = (new PathMatchingResourcePatternResolver()).getResources("classpath*:" + target + ".properties");
            } catch (Exception var18) {
                logger.warn("Resolved classpath location [" + target + "] to resources error:", var18);
            }

            Resource[] var10 = resources;
            int var11 = resources.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                Resource resource = var10[var12];
                String url = null;

                try {
                    url = resource.getURL().getPath();
                } catch (Exception var17) {
                    var17.printStackTrace();
                }

                String baseDir = rootDirPath;
                String prefix;
                if (StringUtils.hasText(url)) {
                    prefix = url.substring(url.lastIndexOf("/" + rootDirPath));
                    baseDir = prefix.substring(1, prefix.lastIndexOf(47) + 1);
                }

                prefix = resource.getFilename().split("\\.")[0].split("_")[0];
                basenameSet.add(baseDir + prefix);
            }
        }

        basenameArr = new String[basenameSet.size()];
        basenameSet.toArray(basenameArr);
        logger.info("Resolved i18n resources: " + basenameSet);
        return basenameArr;
    }

    private String determineRootDir(String location) {
        int prefixEnd = location.indexOf(58) + 1;

        int rootDirEnd;
        for(rootDirEnd = location.length(); rootDirEnd > prefixEnd && (new AntPathMatcher()).isPattern(location.substring(prefixEnd, rootDirEnd)); rootDirEnd = location.lastIndexOf(47, rootDirEnd - 2) + 1) {
        }

        if (rootDirEnd == 0) {
            rootDirEnd = prefixEnd;
        }

        return location.substring(0, rootDirEnd);
    }

    static class ResourceAntPathCondition implements Condition {
        ResourceAntPathCondition() {
        }

        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String basename = context.getEnvironment().getProperty("spring.messages.basename");
            return StringUtils.hasText(basename) ? (new AntPathMatcher()).isPattern(basename) : false;
        }
    }
}
