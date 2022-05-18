package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Message;
import com.light.hexo.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: MessageMapper
 * @ProjectName hexo-boot
 * @Description: 消息 Mapper
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
