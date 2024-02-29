package com.nowcoder.community.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {
    //工具类，生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //Md5加密
    //hello -> abc123456
    //hello + 3e4a -> abc1234566abc
    public static String md5 (String key) {
        if (StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

//    code 意思是编号，msg意思是提示消息
    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
//        转化为json类型的字符串
        return json.toJSONString();
    }

//    在json里code是肯定有的，而msg和map不一定有为了方便初始化，我们再写两个方法
    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString (int code) {
        return getJSONString(code, null, null);
    }
//方便了前后端的交互
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 13);
        System.out.println(getJSONString(0, "ok", map));
    }


}
