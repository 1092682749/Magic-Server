package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.server.FirstServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("all")
@Controller
public class FirstController {
    @Resource
    FirstServer firstServer;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/redPacket")
    public ModelAndView redPacket(){
        System.out.print("进来了");
        return new ModelAndView("redPacket");
    }

    @RequestMapping("/list")
    public @ResponseBody List<User> list(String name){
        List<User> users = firstServer.findAllUser();
        return users;
    }

    @RequestMapping("/list2")
    public void list2(){
//        Object obj;
//        obj = (Object) redisTemplate.execute((RedisOperations ops) -> {
//            ops.boundValueOps("key1").set("abc");
//            ops.boundHashOps("hash").put("hash-key-1","hash-value-1");
//            return ops.boundValueOps("key1").get();
//        });
//        System.out.println(obj);
    }
    @RequestMapping("/index1")
    public ModelAndView index1(){
        return new ModelAndView("index1");
    }
}
