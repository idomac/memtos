package com.quanix.memtos.server.support.jms;

import com.quanix.memtos.server.support.email.MimeMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * created by lihaoquan
 * 消息的异步被动接收者.
 */
public class NotifyMessageListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageListener.class);

    @Autowired(required = false)
    private MimeMailService mimeMailService;

    /**
     * 监听事件信息发送
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            // 打印消息详情
            logger.info("UserName:{}, OrganizationId:{}",
                    mapMessage.getString("userName"),
                    mapMessage.getString("organizationId")==null?"None":mapMessage.getString("organizationId"));

            // 发送邮件
            if (mimeMailService != null) {
                mimeMailService.send(mapMessage.getString("userName"));
            }
        }catch (Exception e) {
            logger.error("处理消息时发生异常.", e);
        }
    }
}
