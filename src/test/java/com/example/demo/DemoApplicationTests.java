package com.example.demo;

import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void contextLoads() { }
    @Test
    public void redisScript(){
        User user = new User();
        user.setId(12);
        user.setUsername("fffff");
        String inS = "redis.call(\"hmset\",\"user\",KEYS[1],KEYS[2],ARGV[1],ARGV[2])";
        String changeS = "local name = KEYS[1]\n" +
                "local value = KEYS[2]\n" +
                "redis.call(\"hset\",\"user\",name,value)";
        Jedis jedis = new Jedis("localhost",6379);
        jedis.eval(inS,2,"name","id",user.getUsername(), ""+user.getId());
        jedis.eval(changeS,2,"name","llllllll");
    }

}
