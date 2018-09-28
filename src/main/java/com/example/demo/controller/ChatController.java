package com.example.demo.controller;

import com.example.demo.model.ChatMsg;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.User;
import com.example.demo.server.ChatMsgRecordService;
import com.example.demo.server.ChatMsgService;
import com.example.demo.server.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/ok")
public class ChatController {
    @Resource
    UserService userService;
    @Resource
    ChatMsgService chatMsgService;
    @Resource
    ChatMsgRecordService chatMsgRecordService;
    @RequestMapping("/page")
    public String registPage() {
        return "regist";
    }

    @RequestMapping("/save")
    public  ModelAndView save(User user) throws Exception {
        User userFromDB = userService.findByUsername(user.getUsername());
        ModelAndView mav = new ModelAndView("regist");
        if (userFromDB != null) {
            mav.addObject("msg","用户已存在");
            return mav;
        }
        int count = userService.save(user);
        if (count < 0) {
            System.out.println("保存失败");
            mav.addObject("msg","服务器原因保存失败");
            return mav;
        }
        return new ModelAndView("login");
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
    @RequestMapping("/getMsgListById")
    public @ResponseBody List<ChatMsg> getMsgListById(Integer sendId, String reviceName) throws Exception {
        User reviceUser = userService.findByUsername(reviceName);
        List<ChatMsg> chatMsgList = chatMsgService.findByUser(sendId, reviceUser.getId());
        List<ChatMsg> chatMsgList1 = chatMsgService.findByUser(reviceUser.getId(), sendId);
        chatMsgList.addAll(chatMsgList1);
        Collections.sort(chatMsgList);
        return chatMsgList;
    }

    @RequestMapping("/getMsgListByName")
    public @ResponseBody List<ChatMsgRecord> getMsgListByName(String sendName, String receiveName) throws Exception {
        List<ChatMsgRecord> chatMsgList = chatMsgRecordService.findByUser(sendName, receiveName);
        List<ChatMsgRecord> chatMsgList1 = chatMsgRecordService.findByUser(receiveName, sendName);
        if (chatMsgList1 != null) {
            chatMsgList.addAll(chatMsgList1);
        }
        Collections.sort(chatMsgList);
        return chatMsgList;
    }

    @RequestMapping("/already")
    public @ResponseBody void already(String sendName, String receiveName) {
        chatMsgRecordService.already(sendName,receiveName);
    }
}
