package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.ThemeExtend;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ThemeExtendMapper
 * @ProjectName hexo-boot
 * @Description: 主题配置扩展 Mapper
 */
@Repository
public interface ThemeExtendMapper extends BaseMapper<ThemeExtend> {

    /**
     * 插入/修改配置
     * @param list
     */
    void updateBatchByConfigName(@Param("list") List<ThemeExtend> list);

    /**
     * 修改配置
     * @param themeExtendList
     */
    void updateBatchById(@Param("list") List<ThemeExtend> themeExtendList);
}
