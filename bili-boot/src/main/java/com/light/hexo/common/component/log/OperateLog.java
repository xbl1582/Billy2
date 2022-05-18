package com.light.hexo.common.component.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: OperateLog
 * @ProjectName hexo-boot
 * @Description: 操作日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperateLog {

    /**
     * 描述
     * @return
     */
    String value() default "";

    /**
     * 操作类型
     * @return
     */
    ActionEnum actionType();
}
