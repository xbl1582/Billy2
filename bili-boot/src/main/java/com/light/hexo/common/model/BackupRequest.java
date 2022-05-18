package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.Backup;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BackupRequest
 * @Description: 备份请求对象
 */
@Setter
@Getter
public class BackupRequest extends BaseRequest<Backup> {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;
}
