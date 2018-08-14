package com.xingdong.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xingdong.dubbo.test.IProviderDubbo;
import com.xingdong.entity.XdUser;
import com.xingdong.web.dao.XdUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liushuangbo on 2018/8/10.
 */
@Service
public class SenderService {

    @Reference
    private IProviderDubbo providerDubbo;
    @Autowired
    private XdUserMapper xdUserMapper;

    public String getMsg() {
        List<XdUser> users = xdUserMapper.selectByFilter(new XdUser());
        System.err.println(users.size());
        System.err.println("sender requesting...");
        return providerDubbo.queryMsg();
    }


}
