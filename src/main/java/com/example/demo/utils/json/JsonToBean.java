package com.example.demo.utils.json;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;

public class JsonToBean {
    public static Object chagneObject(String JsonStr, Class<?> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(JsonStr);
        return jsonObject.toJavaObject(clazz);
    }
}
