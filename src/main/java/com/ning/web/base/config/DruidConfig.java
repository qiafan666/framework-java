package com.ning.web.base.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Slf4j
@Getter
@Setter
@Configuration
@MapperScan(value = "com.ning.web.mapper")
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfig {

    private String username;
    private String password;
    private String url;
    private String driverClassName;
    private String validationQuery;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setValidationQuery(validationQuery);
        return dataSource;
    }
}
