package com.base.frame.util;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;

/**
 * token工具类
 *
 * */
public class TokenUtil {

    public static String generator(Integer userId, String key){
        String data = JSON.toJSONString(new HashMap<String, Object>(){{
            put("time", System.currentTimeMillis());
            put("id", userId);
        }});
        return AESUtil.encrypt(data, key, 0);
    }

}
