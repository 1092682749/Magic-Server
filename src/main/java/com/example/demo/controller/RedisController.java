package com.example.demo.controller;

import com.example.demo.configration.session.MySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/ok")
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/set")
    public void set(HttpSession session, HttpServletResponse response) {
        response.addCookie(new Cookie("token", "99999999"));
        Map<String, Object> map = new HashMap<>();
        map.put("isLogin", "true");

        redisTemplate.opsForValue().set("99999999", "true");
        //re
    }

    @RequestMapping("/ward")
    public String ward(HttpServletRequest request) {
        String[] strs = {"yyyy", "mmmm", "kkkk"};
        boolean is = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("token".equals(name)) {

                if ("true".equals(redisTemplate.opsForValue().get(cookie.getValue()))) {
                    is = true;
                }
            }
        }
        if (is) {
            Random random = new Random();
            int i = random.nextInt(strs.length);
            return strs[i];
        }
        return "你还没有登陆呢";
    }

    @RequestMapping("redisTest")
    public String redisTest() {
        return "test/redisTest";
    }

}
