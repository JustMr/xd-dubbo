package com.xingdong.test.utility;

import com.xingdong.util.ws.CxfUtil;
import org.junit.Test;

/**
 *
 * Created by liushuangbo on 2018/8/29.
 */
public class CxfUtilTest {

    @Test
    public void testCxf() {
        try {
            System.out.println(CxfUtil.sendCxfMessageRetOne("http://localhost:8081/demo/cxf/remoteService?wsdl",
                    "showMessage", "还好有你"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
