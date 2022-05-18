package com.light.hexo.business.admin.service;

import com.light.hexo.business.admin.model.Attachment;
import com.light.hexo.common.base.BaseService;
import com.light.hexo.common.component.file.FileRequest;
import com.light.hexo.common.exception.GlobalException;

import java.util.List;

/**
 * @ClassName: AttachmentService
 * @ProjectName hexo-boot
 * @Description: 附件 Service
 */
public interface AttachmentService extends BaseService<Attachment> {

    /**
     * 批量删除附件
     * @param idStrList
     * @throws GlobalException
     */
    void removeAttachmentBatch(List<String> idStrList) throws GlobalException;

}
