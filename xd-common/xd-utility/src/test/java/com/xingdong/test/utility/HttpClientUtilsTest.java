package com.xingdong.test.utility;

import com.xingdong.util.common.HttpClientUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Http测试
 * Created by liushuangbo on 2018/8/28.
 */
public class HttpClientUtilsTest {


    @Test
    public void postTests() {
        String url = "http://localhost:8081/demo/test/http/only";
//        String url = "http://localhost:8081/demo/test/http/only1";
//        String url = "http://localhost:8081/demo/test/dubbo/sender";
        double charge = 5.155;
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("name", "liushuangbo");
        mapParams.put("charge", String.valueOf(charge));
        String ret = HttpClientUtils.URLPost(url, mapParams, "utf-8");
        try {
            System.err.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ret1 = HttpClientUtils.URLGet(url, mapParams, "utf-8");
        try{
            System.err.println(ret1);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
