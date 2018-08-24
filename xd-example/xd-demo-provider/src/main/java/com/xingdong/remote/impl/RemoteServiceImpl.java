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
@WebService(endpointInterface = "com.xingdong.remote.IRemoteService",
        serviceName = "msgService")
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
