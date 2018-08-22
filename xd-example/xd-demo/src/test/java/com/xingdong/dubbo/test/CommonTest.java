package com.xingdong.dubbo.test;

import com.xingdong.common.util.SpringContextUtil;
import com.xingdong.mq.provider.MqProviderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by liushuangbo on 2018/8/22.
 */
public class CommonTest {

    @Test
    public void testBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath*:spring/*.xml");
        MqProviderService service = SpringContextUtil.getBean(MqProviderService.class);
        service.sendMessage("zhongyukyida konggele");
    }
}
