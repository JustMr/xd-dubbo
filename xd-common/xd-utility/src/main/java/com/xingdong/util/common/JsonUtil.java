package com.xingdong.util.common;

import com.alibaba.fastjson.JSONObject;

/**
 * FastJSON
 * Created by liushuangbo on 2018/8/23.
 */
public class JsonUtil {

    public static String toJSON(Object o) {
        return JSONObject.toJSONString(o);
    }

}
