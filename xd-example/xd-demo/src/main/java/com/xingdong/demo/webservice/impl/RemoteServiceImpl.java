package com.xingdong.demo.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import com.xingdong.demo.webservice.IRemoteService;
import com.xingdong.entity.XdUser;

import javax.jws.WebService;
import java.util.List;

/**
 * Webservice 服务
 * Created by liushuangbo on 2018/8/23.
 */
// targetNamespace 实现类和接口不在同包下，需要指定接口类所在包（路径）定义的命名空间
// 同包下不需要指定 targetNamespace
@WebService(endpointInterface = "com.xingdong.demo.webservice.IRemoteService",
        serviceName = "msgService", targetNamespace = "http://webservice.demo.xingdong.com/")
public class RemoteServiceImpl implements IRemoteService {

    @Override
    public String showMessage(String msg) {
        return "your message is " + msg;
    }

    @Override
    public String addUser(List<XdUser> list) {
        return JSONObject.toJSONString(list);
    }
}
