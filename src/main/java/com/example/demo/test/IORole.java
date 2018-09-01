package com.example.demo.test;

import com.example.demo.model.RedisIO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IORole {

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/io")
    public @ResponseBody RedisIO test(){
        RedisIO redisIO = new RedisIO();
        redisIO.setCards("01");
        redisIO.setInfo("i am 01");
        redisTemplate.opsForValue().set("01",redisIO);
        RedisIO redisIO1 = (RedisIO) redisTemplate.opsForValue().get("01");
        System.out.print(redisIO1.getInfo());
        return redisIO1;
    }
}
