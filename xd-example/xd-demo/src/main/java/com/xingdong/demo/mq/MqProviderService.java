package com.xingdong.demo.mq;

import com.xingdong.util.MqSendUtil;
import org.springframework.stereotype.Service;

/**
 * mq provider 发送者
 * Created by liushuangbo on 2018/8/21.
 */
@Service
public class MqProviderService {

    public void sendMessage(String words) {
        System.out.println("========================");
        System.out.println("开始发送消息");
        System.out.println("========================");
        MqSendUtil.sendMsgQueue("biz-queue", words);
    }

}
