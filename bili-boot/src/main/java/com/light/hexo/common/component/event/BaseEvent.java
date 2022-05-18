package com.light.hexo.common.component.event;

/**
 * @ClassName: BaseEvent
 * @ProjectName hexo-boot
 * @Description: 事件基类
 */
public class BaseEvent {

    protected EventEnum getEventType() {
        return EventEnum.NAV;
    }
}
