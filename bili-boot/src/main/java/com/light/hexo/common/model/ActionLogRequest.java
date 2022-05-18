package com.light.hexo.common.model;

import com.light.hexo.business.admin.model.ActionLog;
import com.light.hexo.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: ActionLogRequest
 * @Description: 操作日志请求对象
 */
@Setter
@Getter
public class ActionLogRequest extends BaseRequest<ActionLog> {


    /**
     * 操作类型，参考 ActionEnum
     */
    private Integer actionType;
}
