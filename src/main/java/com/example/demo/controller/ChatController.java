package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ok")
public class ChatController {
    @Resource
    UserService userService;
    @RequestMapping("/page")
    public String registPage() {
        return "regist";
    }

    @RequestMapping("/save")
    public @ResponseBody String save(User user) {
        int count = userService.save(user);
        if (count < 0) {
            System.out.println("保存失败");
            return "保存失败";
        }
        return "保存成功";
    }
    @RequestMapping("/getUser")
    public @ResponseBody String getUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @RequestMapping("/getFriend")
    public @ResponseBody List<User> getFriend() {
       return userService.findAll();
    }

    @RequestMapping("/checkSession")
    public @ResponseBody Boolean checkSession() {
        Object o =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o instanceof String) {
            return false;
        }
        return true;
    }
}
