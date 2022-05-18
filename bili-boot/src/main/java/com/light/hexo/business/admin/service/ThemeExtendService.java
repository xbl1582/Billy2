package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.ThemeExtend;
import com.light.hexo.business.admin.model.extend.ThemeFileExtension;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThemeExtendService
 * @ProjectName hexo-boot
 * @Description: 主题配置扩展 Service
 */
public interface ThemeExtendService extends BaseService<ThemeExtend> {

    /**
     * 保存主题配置
     * @param themeId
     * @param extension
     * @throws GlobalException
     */
    void saveThemeExtend(Integer themeId, List<ThemeFileExtension> extension) throws GlobalException;

    /**
     * 保存主题配置
     * @param themeExtendList
     * @throws GlobalException
     */
    void saveThemeExtend(List<ThemeExtend> themeExtendList) throws GlobalException;

    /**
     * 通过主题 id 获取主题配置列表
     * @param themeId
     * @return
     * @throws GlobalException
     */
    List<ThemeExtend> listThemeExtends(Integer themeId) throws GlobalException;

    /**
     * 获取主题配置
     * @param themeId
     * @return
     * @throws GlobalException
     */
    Map<String, String> getThemeExtendMap(Integer themeId) throws GlobalException;

    /**
     * 批量删除主题配置
     * @param themeIdList
     * @throws GlobalException
     */
    void deleteThemeExtendBatch(List<Integer> themeIdList) throws GlobalException;
}
