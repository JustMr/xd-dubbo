package com.xingdong.test.redis;

import com.xingdong.ori.redis.OriginalRedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 *
 * Created by liushuangbo on 2018/8/17.
 */
public class RedisTest {

    ClassPathXmlApplicationContext context;

    @Before
    public void loadXML() {
        context = new ClassPathXmlApplicationContext(
                "classpath:/spring/spring-ori-redis.xml");
        context.start();
    }

    @Test
    public void redisConnTest(){
        OriginalRedisUtil redisUtil = (OriginalRedisUtil) context.getBean("originalRedisUtil");
        if (redisUtil.exists("PROVIDER_COUNT")) {
            redisUtil.incr("PROVIDER_COUNT");
        } else {
            redisUtil.setStr("PROVIDER_COUNT", "0");
        }
    }
}
