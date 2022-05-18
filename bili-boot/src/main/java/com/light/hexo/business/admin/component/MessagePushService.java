package com.light.hexo.business.admin.component;

import com.light.hexo.business.admin.component.websocket.MessageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MessagePushService
 * @ProjectName hexo-boot
 * @Description: 消息推送
 */
@Component
@DependsOn("messageServer")
public class MessagePushService {

    @Autowired
    private MessageServer messageServer;

    public void pushMessage() {
        this.messageServer.broadcast();
    }
}
