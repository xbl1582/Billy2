package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Category;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: CategoryMapper
 * @ProjectName hexo-boot
 * @Description: 分类 Mapper
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}