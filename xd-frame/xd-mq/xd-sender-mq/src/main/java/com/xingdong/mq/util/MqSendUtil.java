package com.xingdong.mq.util;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * mq util
 * Created by liushuangbo on 2018/8/21.
 */
public class MqSendUtil {

    public static String getInfo(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            return textMessage.getText();
        }
        return null;
    }


}
