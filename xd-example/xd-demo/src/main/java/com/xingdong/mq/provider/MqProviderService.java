package com.xingdong.mq.provider;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * mq provider
 * Created by liushuangbo on 2018/8/21.
 */
@Service
public class MqProviderService {

    @Resource(name = "jmsTemplateQuene")
    private JmsTemplate jt = null;

    public void sendMessage(final String words) {
        System.out.println("========================");
        System.out.println("开始发送消息");
        System.out.println("========================");
        jt.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(words);
            }
        });
    }

}
