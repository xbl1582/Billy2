package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.Music;
import com.light.hexo.common.base.BaseService;

import java.util.List;

/**
 * @ClassName: MusicService
 * @ProjectName hexo-boot
 * @Description: 音乐 Service
 */
public interface MusicService extends BaseService<Music> {

    /**
     * 批量删除音乐
     * @param idStrList
     */
    void removeMusicBatch(List<String> idStrList);

    /**
     * 获取音乐列表（首页）
     * @return
     */
    List<Music> listMusicByIndex();
}
