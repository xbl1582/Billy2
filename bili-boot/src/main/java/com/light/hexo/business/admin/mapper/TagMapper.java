package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Tag;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: TagMapper
 * @ProjectName hexo-boot
 * @Description: 标签 Mapper
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<Tag> list);

    /**
     * 批量查询标签
     * @param tagNames
     * @return
     */
    List<Tag> selectListByTags(@Param("tagNames") String[] tagNames);

    /**
     * 根据名称获取标签
     * @param name
     * @return
     */
    Tag selectOneByName(@Param("name") String name);
}
