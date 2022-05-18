package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.Dynamic;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.component.event.EventService;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;

/**
 * @ClassName: DynamicService
 * @ProjectName hexo-boot
 * @Description: 动态 Service
 */
public interface DynamicService extends BaseService<Dynamic>, EventService {

    /**
     * 保存动态
     * @param dynamic
     */
    void saveDynamic(Dynamic dynamic) throws GlobalException;

    /**
     * 修改动态
     * @param dynamic
     * @throws GlobalException
     */
    void updateDynamic(Dynamic dynamic) throws GlobalException;

    /**
     * 批量删除动态
     * @param idStrList
     */
    void removeDynamicBatch(List<String> idStrList) throws GlobalException;

    // ======================== 前端接口 ================================

    /**
     * 动态列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Dynamic> listDynamicByIndex(Integer pageNum, int pageSize);

    /**
     * 点赞动态
     * @param ipAddr
     * @param dynamicId
     * @return
     */
    int praiseDynamic(String ipAddr, Integer dynamicId);
}
