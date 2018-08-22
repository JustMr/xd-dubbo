package com.xingdong.dubbo.consumer;

import com.xingdong.demo.service.SenderService;
import com.xingdong.mq.provider.MqProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * dubbo请求端
 * Created by liushuangbo on 2018/8/10.
 */
@Controller
@RequestMapping("/test")
public class SenderDubbo {

    @Resource
    private SenderService senderService;
    @Resource(name = "mqProviderService")
    private MqProviderService mqProviderService;


    @RequestMapping("/dubbo/sender")
    @ResponseBody
    public String senderDubbo() {
        return senderService.getMsg();
    }


    @RequestMapping("/mq/sender/{words}")
    @ResponseBody
    public String senderMQ(@PathVariable("words")String words) {
        mqProviderService.sendMessage(words);
        return "[" + words + "]发送完成";
    }

}
