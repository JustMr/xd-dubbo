package com.xingdong.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xingdong.dubbo.test.IProviderDubbo;
import com.xingdong.entity.XdUser;
import com.xingdong.util.redis.BaseRedisUtil;
import com.xingdong.web.dao.XdUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Dubbo 接口调用
 * Created by liushuangbo on 2018/8/10.
 */
@Service
public class SenderService {

    @Reference
    private IProviderDubbo providerDubbo;
    @Autowired
    private XdUserMapper xdUserMapper;
    @Resource(name = "baseRedisUtil")
    private BaseRedisUtil redisUtil;

    public String getMsg() {
        List<XdUser> users = xdUserMapper.selectByFilter(new XdUser());
        System.err.println(users.size());

        if(redisUtil.hasKey("CUSTOMER_COUNT")) {
            redisUtil.incr("CUSTOMER_COUNT", 1);
        } else {
            redisUtil.set("CUSTOMER_COUNT", "0");
        }

        System.err.println("sender requesting...");
        return providerDubbo.queryMsg();
    }


}
