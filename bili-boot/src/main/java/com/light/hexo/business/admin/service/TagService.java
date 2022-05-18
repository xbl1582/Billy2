package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.Tag;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.component.event.EventService;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;

/**
 * @ClassName: TagService
 * @ProjectName hexo-boot
 * @Description: 标签 Service
 */
public interface TagService extends BaseService<Tag>, EventService {

    /**
     * 批量删除标签
     * @param idStrList
     */
    void removeTagBatch(List<String> idStrList) throws GlobalException;

    /**
     * 批量保存标签
     * @param tagNames
     * @return
     * @throws GlobalException
     */
    List<Integer> saveTagBatch(String[] tagNames) throws GlobalException;

    /**
     * 获取标签数量
     * @return
     * @throws GlobalException
     */
    Integer getTagNum() throws GlobalException;

    // =========================== 以下为前端页面请求 ============================

    /**
     * 标签列表（首页）
     * @return
     * @throws GlobalException
     */
    List<Tag> listTagsByIndex() throws GlobalException;

    /**
     * 根据名称获取标签
     * @param tagName
     * @return
     * @throws GlobalException
     */
    Tag findByTagName(String tagName) throws GlobalException;
}
