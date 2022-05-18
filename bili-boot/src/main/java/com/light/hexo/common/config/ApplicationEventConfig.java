package com.light.hexo.common.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @ClassName: ApplicationEventConfig
 * @Description: 事件相关配置

 */
@Configuration
public class ApplicationEventConfig {

    @Bean
    public ApplicationEventPublisher createApplicationEventPublisher() {
        return new AnnotationConfigWebApplicationContext();
    }
}
