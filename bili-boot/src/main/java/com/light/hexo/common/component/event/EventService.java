package com.light.hexo.common.component.event;

/**
 * @ClassName: EventService
 * @ProjectName hexo-boot
 * @Description: 事件处理 Service
 */
public interface EventService {

    EventEnum getEventType();

    void dealWithEvent(BaseEvent event);
}
