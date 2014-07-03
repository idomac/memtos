package com.quanix.memtos.server.support.jms;

import com.quanix.memtos.server.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * created by lihaoquan
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 */
public class NotifyMessageProducer {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageProducer.class);

    private JmsTemplate jmsTemplate;
    private Destination notifyQueue;
    private Destination notifyTopic;

    public void sendQueue(final User user) {
        sendMessage(user, notifyQueue);
    }

    public void sendTopic(final User user) {
        sendMessage(user, notifyTopic);
    }


    /**
     * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
     */
    private void sendMessage(User user, Destination destination) {
        Map map = new HashMap();
        map.put("userName", user.getUsername());
        map.put("organizationId", user.getOrganizationId());
        jmsTemplate.convertAndSend(destination, map);
    }



    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setNotifyQueue(Destination notifyQueue) {
        this.notifyQueue = notifyQueue;
    }

    public void setNotifyTopic(Destination nodifyTopic) {
        this.notifyTopic = nodifyTopic;
    }

}
