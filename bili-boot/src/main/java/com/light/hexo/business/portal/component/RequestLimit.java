package com.light.hexo.business.portal.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: RequestLimit
 * @ProjectName hexo-boot
 * @Description: 请求限制注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {

    /**
     * 缓存 name
     * @return
     */
    String cacheName();

    /**
     * 限制时间
     * @return
     */
    int time();

    /**
     * 提示
     * @return
     */
    String msg();
}
