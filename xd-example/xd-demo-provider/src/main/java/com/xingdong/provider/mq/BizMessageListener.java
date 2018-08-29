package com.xingdong.provider.mq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * mq 消费者
 * Created by liushuangbo on 2018/8/21.
 */
public class BizMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String mes = textMessage.getText();
                System.err.println("===========================");
                System.err.println(mes);
                System.err.println("===========================");
            } else {
                System.err.println("===========================");
                System.err.println("其他类型：");
                System.err.println(message.getJMSType());
                System.err.println("===========================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
