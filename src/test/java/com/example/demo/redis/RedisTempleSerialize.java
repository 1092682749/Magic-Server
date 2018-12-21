package com.example.demo.redis;

import com.example.demo.DemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

public class RedisTempleSerialize extends DemoApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test() {
        Long l = 1L;
        T t = new T();
        t.setaLong(111L);
        redisTemplate.opsForValue().set("l", l);
        redisTemplate.opsForValue().set("ll", t);
        System.out.println(((T)redisTemplate.opsForValue().get("ll")).getaLong());
        System.out.println(redisTemplate.opsForValue().get("l"));
    }
}
class T implements Serializable {
    Long aLong;

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }
}
