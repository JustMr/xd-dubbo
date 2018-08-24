package com.xingdong.remote.impl;

import com.alibaba.fastjson.JSONObject;
import com.xingdong.entity.XdUser;
import com.xingdong.remote.IRemoteService;

import javax.jws.WebService;
import java.util.List;

/**
 *
 * Created by liushuangbo on 2018/8/23.
 */
// targetNamespace 实现类和接口不在同包下，需要指定接口类所在包（路径）定义的命名空间
// 同包下不需要指定 targetNamespace
@WebService(endpointInterface = "com.xingdong.remote.IRemoteService",
        serviceName = "msgService", targetNamespace = "http://remote.xingdong.com/")
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
