package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.PostTag;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PostTagMapper
 * @ProjectName hexo-boot
 * @Description: 文章标签 Mapper
 */
@Repository
public interface PostTagMapper extends BaseMapper<PostTag> {

    /**
     * 获取文章标签
     * @param postId
     * @return
     */
    List<String> selectTagNameByPostId(Integer postId);

    /**
     * 批量保存文章标签关系
     * @param list
     */
    void insertPostTagBatch(@Param("list") List<PostTag> list);

    /**
     * 删除记录
     * @param postId
     */
    void deleteByPostId(Integer postId);
}
