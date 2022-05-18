package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Post;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PostMapper
 * @ProjectName hexo-boot
 * @Description: 文章 Mapper
 */
@Repository
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取上一页信息
     * @param id
     * @return
     */
    Post selectPreviousInfo(@Param("id") Integer id);

    /**
     * 获取下一页信息
     * @param id
     * @return
     */
    Post selectNextInfo(@Param("id") Integer id);

    /**
     *
     * @param tagId
     * @return
     */
    List<Post> selectListByTagId(@Param("tagId") Integer tagId);

    /**
     * 检测插入
     * @param post
     */
    void checkInsert(Post post);
}
