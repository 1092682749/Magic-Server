package com.example.demo.json;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.RedisIO;

public class FastJsonBug {
    public static void main(String[] args) {
        RedisIO redisIO = new RedisIO();
        redisIO.setTestInfo("aaaaaaaa");
        System.out.println(JSON.toJSONString(redisIO));
    }
}
