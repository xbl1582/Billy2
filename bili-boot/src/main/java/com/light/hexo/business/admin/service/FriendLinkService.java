package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.FriendLink;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.component.event.EventService;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;

/**
 * @ClassName: FriendLinkService
 * @ProjectName hexo-boot
 * @Description: 友链 Service
 */
public interface FriendLinkService extends BaseService<FriendLink>, EventService {

    /**
     * 批量删除友链
     * @param idStrList
     * @throws GlobalException
     */
    void removeFriendLinkBatch(List<String> idStrList) throws GlobalException;

    /**
     * 新增友链
     * @param friendLink
     * @throws GlobalException
     */
    void saveFriendLink(FriendLink friendLink) throws GlobalException;

    /**
     * 修改友链
     * @param friendLink
     * @throws GlobalException
     */
    void updateFriendLink(FriendLink friendLink) throws GlobalException;

    // =========================== 以下为前端页面请求 ============================

    List<FriendLink> listFriendLinkByIndex() throws GlobalException;

    /**
     * 获取友链数
     * @return
     * @throws GlobalException
     */
    Integer getFriendLinkNum() throws GlobalException;
}
