package com.xingdong.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.xingdong.dubbo.test.IProviderDubbo;
import com.xingdong.provider.service.ProviderService;

import javax.annotation.Resource;

/**
 * dubbo服务提供者
 * Created by liushuangbo on 2018/8/10.
 */
@Service
public class ProviderDubbo implements IProviderDubbo {

    @Resource
    private ProviderService providerService;

    @Override
    public String queryMsg() {
        return providerService.getMsg();
    }
}
