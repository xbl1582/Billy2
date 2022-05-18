package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.PostTag;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;

/**
 * @ClassName: PostTagService
 * @ProjectName hexo-boot
 * @Description: 文章标签 Service
 */
public interface PostTagService extends BaseService<PostTag> {

    /**
     * 获取文章标签
     * @param postId
     * @return
     * @throws GlobalException
     */
    List<String> listTagNamesByPostId(Integer postId) throws GlobalException;

    /**
     * 批量保存文章标签
     * @param list
     * @throws GlobalException
     */
    void savePostTagBatch(List<PostTag> list) throws GlobalException;

    /**
     * 删除文章标签关联
     * @param postId
     * @throws GlobalException
     */
    void deletePostTag(Integer postId) throws GlobalException;
}
