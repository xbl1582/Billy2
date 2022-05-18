package com.light.hexo.common.exception;

/**
 * @ClassName: GlobalExceptionMap
 * @ProjectName hexo-boot
 * @Description: 异常枚举接口
 */
public interface GlobalExceptionMap {

    /**
     * 返回 code
     * @return
     */
    int getCode();

    /**
     * 返回消息
     * @return
     */
    String getMessage();
}
