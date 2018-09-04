package com.xingdong.provider.test;

import com.xingdong.common.test.XdTest;
import com.xingdong.common.util.SpringContextUtil;
import com.xingdong.provider.service.ProviderService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by liushuangbo on 2018/8/22.
 */
public class CommonTest extends XdTest {

    @Test
    public void testBean() {
        ProviderService service = SpringContextUtil.getBean(ProviderService.class);
        service.getMsg();
    }

    @Test
    public void testCxfMain() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8081/dubbo/cxf/remoteService?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("showMessage", "动态access");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

}
