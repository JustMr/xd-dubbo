package com.xingdong.ws.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * WebService cxf
 * Created by liushuangbo on 2018/8/24.
 */
public class CxfUtil {

    public static Object sendCxfMessageRetOne(String wsdlUrl, String operationName,
                                        Object... params) throws Exception {
        Object[] o = sendCxfMessage(wsdlUrl, operationName, params);
        return o[0];
    }

    public static Object sendCxfMessageRetArray(String wsdlUrl, String operationName,
                                           Object... params) throws Exception {
        return sendCxfMessage(wsdlUrl, operationName, params);
    }


    private static Object[] sendCxfMessage(String wsdlUrl, String operationName,
                                           Object... params) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        // invoke("方法名",参数1,参数2,参数3....);
        objects = client.invoke(operationName, params);
        return objects;
    }
}
