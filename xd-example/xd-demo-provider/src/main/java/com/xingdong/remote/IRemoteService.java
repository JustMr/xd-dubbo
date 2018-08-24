package com.xingdong.remote;

import com.xingdong.entity.XdUser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 *
 * Created by liushuangbo on 2018/8/23.
 */
@WebService
public interface IRemoteService {

    @WebMethod
    String showMessage(@WebParam(name = "msg0") String msg);

    @WebMethod
    String addUser(List<XdUser> list);
}
