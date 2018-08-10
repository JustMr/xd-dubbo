package com.xingdong.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xingdong.demo.service.SenderService;
import com.xingdong.dubbo.test.IProviderDubbo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * dubbo请求端
 * Created by liushuangbo on 2018/8/10.
 */
@Controller
@RequestMapping("/test/dubbo")
public class SenderDubbo {

    @Resource
    private SenderService senderService;
    @Reference
    private IProviderDubbo providerDubbo;

    @RequestMapping("/sender")
    @ResponseBody
    public String senderDubbo() {
        System.out.println(senderService.getMsg());
        return providerDubbo.queryMsg();
    }

}
