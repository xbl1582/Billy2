package com.light.hexo.common.constant;

/**
 * @ClassName: CacheKey
 * @Description: 搭配 CacheUtil 使用
 */
public final class CacheKey {

    /**
     * 首页数量信息
     */
    public static final String INDEX_COUNT_INFO = "index:count:info";

    /**
     * 导航列表缓存 key
     */
    public static final String NAV_LIST = "nav:list";

    /**
     * 黑名单列表
     */
    public static final String BLACK_LIST = "hexo:black:list";

    /**
     * 全局配置列表
     */
    public static final String CONFIG_LIST = "hexo:config:list";

    /**
     * 当前使用的主题
     */
    public static final String CURRENT_THEME = "hexo:current:theme";

    /**
     * 登录失败次数
     */
    public static final String LOGIN_ERROR_NUM = "hexo:login:error:num";
}
