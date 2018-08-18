package com.xingdong.test.redis;

import com.xingdong.util.redis.BaseRedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by liushuangbo on 2018/8/17.
 */
public class RedisTest {

    ClassPathXmlApplicationContext context;
    private final static long ONE = 1;

    @Before
    public void loadXML() {
        context = new ClassPathXmlApplicationContext(
                "classpath:/spring/spring-redis.xml");
        context.start();
    }

    @Test
    public void redisConnTest(){
        BaseRedisUtil redisUtil = (BaseRedisUtil) context.getBean("baseRedisUtil");
        for (int i=0; i<3;i++) {
            if (redisUtil.hasKey("PROVIDER_COUNT_1")) {
                System.err.println("exist");
                redisUtil.incr("PROVIDER_COUNT_1",ONE);
                System.out.println(redisUtil.get("PROVIDER_COUNT_1"));
            } else {
                redisUtil.set("PROVIDER_COUNT_1", "0");
                System.err.println("not exist");
            }
        }

    }
}
