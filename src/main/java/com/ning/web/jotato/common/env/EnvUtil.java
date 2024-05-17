package com.ning.web.jotato.common.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvUtil {
    private static Environment env;

    public EnvUtil() {
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

    public static boolean containsProperty(String key) {
        return env.containsProperty(key);
    }

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return env.getProperty(key, defaultValue);
    }

    public static <T> T getProperty(String key, Class<T> targetType) {
        return env.getProperty(key, targetType);
    }

    public static <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return env.getProperty(key, targetType, defaultValue);
    }

    public static String getRequiredProperty(String key) throws IllegalStateException {
        return env.getRequiredProperty(key);
    }

    public static <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return env.getRequiredProperty(key, targetType);
    }

    public static String resolvePlaceholders(String text) {
        return env.resolvePlaceholders(text);
    }

    public static String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return env.resolveRequiredPlaceholders(text);
    }

    public static String[] getActiveProfiles() {
        return env.getActiveProfiles();
    }

    public static String[] getDefaultProfiles() {
        return env.getDefaultProfiles();
    }

    /** @deprecated */
    @Deprecated
    public boolean acceptsProfiles(String... profiles) {
        return env.acceptsProfiles(profiles);
    }
}
