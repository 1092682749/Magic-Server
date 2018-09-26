package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.server.FirstServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@SuppressWarnings("all")
@Controller
public class FirstController {
    @Resource
    FirstServer firstServer;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/redPacket")
    public ModelAndView redPacket(@RequestParam(defaultValue = "1") Integer i){
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
//        System.out.println(discardServerHandler);
        return new ModelAndView("index1");
    }

    @RequestMapping("/go")
    public void go(){

    }
    @PreAuthorize("hasRole('user')")
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "home";
    }

    @RequestMapping("/socket1")
    public String socket1() {
        return "socket1";
    }


    @RequestMapping("/copy")
    public String copy() {
        return "copy";
    }
}
