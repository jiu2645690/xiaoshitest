package com.xiaoshi.order.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *  Json工具类
*/
public class JsonUtil {
    /**
    *  返回 Json字符串
    */
    public static String toJson(Object object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteClassName);
    }

    /**
     *  将 json 字符串 反序列化成 object 对象
     */
    public static Object toObject(String json,Object object) {
        return JSONObject.parseObject(json,object.getClass());
    }

}
