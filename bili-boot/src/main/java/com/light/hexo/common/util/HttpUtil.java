package com.light.hexo.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HttpUtil
 * @ProjectName hexo-boot
 * @Description: Http 工具类
 */
public class HttpUtil {

    private HttpUtil() {}

    /**
     * 是否是 ajax 请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return !StringUtils.isEmpty(request.getHeader("x-requested-with"))
                && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
    }
}
