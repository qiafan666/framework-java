package com.ning.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;
@EnableOpenApi
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.ning.web.*"})
//        exclude = DataSourcePoolMetricsAutoConfiguration.class)
@MapperScan(basePackages ="com.ning.web.mapper" )
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
