package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Config;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ConfigMapper
 * @ProjectName hexo-boot
 * @Description: 配置 Mapper
 */
@Repository
public interface ConfigMapper extends BaseMapper<Config> {

    /**
     * 修改配置
     * @param configList
     */
    void updateByConfigKey(@Param("list") List<Config> configList);
}
