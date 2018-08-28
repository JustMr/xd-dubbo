package com.xingdong.dubbo.consumer;

import com.xingdong.demo.service.SenderService;
import com.xingdong.mq.provider.MqProviderService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Spring MVC
 * Created by liushuangbo on 2018/8/10.
 */
@Controller
@RequestMapping("/test")
public class SenderDubboController {

    private Logger logger = LoggerFactory.getLogger(SenderDubboController.class);

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

    @RequestMapping(value = "/http/only")
    public String onlyHttpRequest(@RequestParam("name") String name, @RequestParam("charge") double charge) {
        logger.error("======================================");
        logger.error("name:[" + name + "],charge:["+ charge+"]");
        logger.error("======================================");
        return "page/success";
    }

    @RequestMapping(value = "/http/only1")
    public String onlyHttpRequest(HttpServletRequest request) {
        logger.error("======================================");
        logger.error("name:[" + request.getParameter("name") + "]," +
                "charge:["+ request.getParameter("charge")+"]");
        logger.error("======================================");
        return "page/success";
    }

}
