package com.light.hexo.common.base;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName: BaseMapper
 * @ProjectName hexo-boot
 * @Description: Mapper 基类
 */
@RegisterMapper
@Component
public interface BaseMapper <T> extends Mapper<T>, MySqlMapper<T> {
}
