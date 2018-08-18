package com.xingdong.provider.service;

import com.xingdong.util.redis.BaseRedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring
 * Created by liushuangbo on 2018/8/10.
 */
@Service
public class ProviderService {

    @Resource
    private BaseRedisUtil redisUtil;

    public String getMsg(){
        if (redisUtil.hasKey("PROVIDER_COUNT")) {
            redisUtil.incr("PROVIDER_COUNT",1);
        } else {
            redisUtil.set("PROVIDER_COUNT", "0");
        }
        return "msg provided done";
    }

}
