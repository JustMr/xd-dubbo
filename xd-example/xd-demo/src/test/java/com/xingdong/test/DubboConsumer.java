package com.xingdong.test;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * Created by liushuangbo on 2018/8/9.
 */
@Component
public class DubboConsumer {

    @Reference
    private static IProviderDubbo providerDubbo;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (i++ < 5) {
            SayHello();
            Thread.sleep(3000);
        }
    }

    /**
     * 客户端方法中调用服务端的service
     */
    private static void SayHello() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring/dubbo-config.xml"});
        context.start();
        System.err.println(providerDubbo.queryMsg());
    }
}
