package com.xingdong.test;

import com.xingdong.common.test.XdTest;
import com.xingdong.common.util.SpringContextUtil;
import com.xingdong.demo.mq.MqProviderService;
import org.junit.Test;

/**
 *
 * Created by liushuangbo on 2018/8/22.
 */
public class CommonTest extends XdTest {

    @Test
    public void testBean() {
        MqProviderService service = SpringContextUtil.getBean(MqProviderService.class);
        service.sendMessage("zhongyukyida konggele");
    }
}
