package com.example.demo.redis;

import com.example.demo.DemoApplication;
import com.example.demo.DemoApplicationTests;
import com.example.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.xml.ws.Action;

public class EncodingTest extends DemoApplicationTests {
//    @Autowired
//    RedisTemplate redisTemplate;
//    @Test
//    public void test(){
//        User user = new User();
//        user.setUsername("momo");
//        redisTemplate.opsForValue().set("user",user);
//        User ref = (User) redisTemplate.opsForValue().get("user");
//        System.out.println(ref.getUsername());
//    }
    @Test
    public void test() {
        Jedis jedis = new Jedis("dyzhello.club", 6379,1000000);
        jedis.auth("dyz");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
    }

}
