package com.example.demo.redis;

import com.example.demo.DemoApplication;
import com.example.demo.DemoApplicationTests;
import com.example.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.xml.ws.Action;

public class EncodingTest extends DemoApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test(){
        User user = new User();
        user.setUserame("momo");
        redisTemplate.opsForValue().set("user",user);
        User ref = (User) redisTemplate.opsForValue().get("user");
        System.out.println(ref.getUsername());
    }

}
