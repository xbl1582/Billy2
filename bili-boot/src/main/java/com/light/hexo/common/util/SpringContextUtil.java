package com.light.hexo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextUtil
 * @ProjectName hexo-boot
 * @Description: Spring 上下文
 */
@Component
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<?> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static <T> T getBeanByType(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

}
