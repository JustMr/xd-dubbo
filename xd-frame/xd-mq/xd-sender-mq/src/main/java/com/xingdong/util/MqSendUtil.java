package com.xingdong.util;

import com.xingdong.common.util.SpringContextUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * mq util
 * Created by liushuangbo on 2018/8/21.
 */
public class MqSendUtil {

    private static JmsTemplate jmsQueue = null;
    private static JmsTemplate jmsTopic = null;

    static {
        jmsQueue = SpringContextUtil.getBean("jmsTemplateQueue");
        jmsTopic = SpringContextUtil.getBean("jmsTemplateTopic");
    }

    /**
     * 队列模式
     * @param dest 队列名称
     * @param msg 消息内容
     */
    public static void sendMsgQueue(String dest, final String msg) {
        jmsQueue.send(dest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 发布/订阅模式
     * @param dest 队列名称
     * @param msg 消息内容
     */
    public static void sendMsgTopic(String dest, final String msg) {
        jmsTopic.send(dest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public static String getInfo(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            return textMessage.getText();
        }
        return null;
    }


}
