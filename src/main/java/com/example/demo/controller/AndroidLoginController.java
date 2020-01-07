package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AndroidLoginController {

    @Autowired
    UserService userService;
    @RequestMapping("/android/login")
    public @ResponseBody Map<String, String> androidLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse res){
        res.setHeader("Cookie", session.getId());
        HashMap<String, String> response = new HashMap<>();
        try {
            User userDB = userService.findByUsername(user.getUsername());
            if (userDB == null){
                response.put("msg", "不存在该用户!");
                return response;
            }
            if (userDB.getPassword().equals(user.getPassword())){
                response.put("msg", "登录成功!");
//                session.setAttribute("androidUser", userDB);
                System.out.println("session 设置成功!");
            } else {
                response.put("msg", "密码错误!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
