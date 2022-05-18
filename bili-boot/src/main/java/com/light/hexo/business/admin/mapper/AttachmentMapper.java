package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Attachment;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: AttachmentMapper
 * @ProjectName hexo-boot
 * @Description: 附件 Mapper
 */
@Repository
public interface AttachmentMapper extends BaseMapper<Attachment> {


}