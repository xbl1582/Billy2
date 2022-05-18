package com.light.hexo.business.admin.service.impl;

import com.light.hexo.business.admin.constant.HexoExceptionEnum;
import com.light.hexo.business.admin.mapper.ActionLogDetailMapper;
import com.light.hexo.business.admin.mapper.ActionLogMapper;
import com.light.hexo.business.admin.model.ActionLog;
import com.light.hexo.business.admin.model.ActionLogDetail;
import com.light.hexo.business.admin.model.Category;
import com.light.hexo.business.admin.service.ActionLogService;
import com.light.hexo.common.base.BaseMapper;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.base.BaseServiceImpl;
import com.light.hexo.common.component.event.BaseEvent;
import com.light.hexo.common.component.event.EventEnum;
import com.light.hexo.common.component.log.ActionLogEvent;
import com.light.hexo.common.exception.GlobalException;
import com.light.hexo.common.model.ActionLogRequest;
import com.light.hexo.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ActionLogServiceImpl
 * @ProjectName hexo-boot
 * @Description: 操作日志 Service 实现
 */
@Service
@Slf4j
public class ActionLogServiceImpl extends BaseServiceImpl<ActionLog> implements ActionLogService {

    @Autowired
    private ActionLogMapper actionLogMapper;

    @Autowired
    private ActionLogDetailMapper actionLogDetailMapper;

    @Override
    public BaseMapper<ActionLog> getBaseMapper() {
        return this.actionLogMapper;
    }

    @Override
    protected Example getExample(BaseRequest request) {

        ActionLogRequest actionLogRequest = (ActionLogRequest) request;
        Example example = new Example(ActionLog.class);
        Example.Criteria criteria = example.createCriteria();

        Integer actionType = actionLogRequest.getActionType();
        if (actionType != null) {
            criteria.andEqualTo("actionType", actionType);
        }

        example.orderBy("id").desc();

        return example;
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.LOG;
    }

    @Override
    public void dealWithEvent(BaseEvent event) {
        ActionLogEvent logEvent = (ActionLogEvent) event;

        ActionLog actionLog = new ActionLog();
        BeanUtils.copyProperties(logEvent, actionLog);
        actionLog.setUpdateTime(actionLog.getCreateTime());
        this.actionLogMapper.insert(actionLog);

        ActionLogDetail logDetail = new ActionLogDetail();
        logDetail.setLogId(actionLog.getId());
        logDetail.setMethodName(logEvent.getMethodName());
        logDetail.setMethodParam(logEvent.getMethodParam());
        this.actionLogDetailMapper.insert(logDetail);
    }

    @Override
    public ActionLog getActionLogInfo(Integer id) throws GlobalException {

        ActionLog actionLog = super.findById(id);
        if (actionLog == null) {
            ExceptionUtil.throwEx(HexoExceptionEnum.ERROR_ACTION_LOG_NOT_EXIST);
        }

        Example example = new Example(ActionLogDetail.class);
        example.createCriteria().andEqualTo("logId", actionLog.getId());
        ActionLogDetail logDetail = this.actionLogDetailMapper.selectOneByExample(example);

        actionLog.setActionLogDetail(logDetail);

        return actionLog;
    }
}
