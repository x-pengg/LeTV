package com.mylive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chan on 16/4/21.
 */

@Configuration//配置控制
@EnableAutoConfiguration//启用自动配置
@ComponentScan//组件扫描
public class WwW extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WwW.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WwW.class, args);
    }
}
