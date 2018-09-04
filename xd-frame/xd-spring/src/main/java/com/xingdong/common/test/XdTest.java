package com.xingdong.common.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 公共单元测试类
 * Created by liushuangbo on 2018/9/4.
 */
@ContextConfiguration(locations = {
        "classpath*:spring/spring-*.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class XdTest {
}
