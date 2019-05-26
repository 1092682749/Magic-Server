package com.example.demo.controller;

import com.example.demo.model.FriendApplication;
import com.example.demo.model.RedisIO;
import com.example.demo.model.User;
import com.example.demo.service.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Controller
public class FirstController {
    @Resource
    FirstServer firstServer;
    @Resource
    UserService userService;
    @Resource
    ChatMsgRecordService chatMsgRecordService;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    FriendService friendService;
    @Resource
    FriendApplicationService friendApplicationService;
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

    @RequestMapping("index")
    public ModelAndView index() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        // 查找好友
        List<User> userList = friendService.findMyAllFriend();
        List<User> allUser = userService.findAll();
        List<User> appliationUserList = new LinkedList<>();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user",user);
        mav.addObject("allUser",userList);
        // 查找好友申请
        List<Map<String, Object>> applicationList = friendApplicationService.findFriendApplication(user.getId());
        mav.addObject("friendApplicationList", applicationList);

        Map<String,Integer> unreadMap = new HashMap<>();
        for (User u : allUser) {
           Integer count = chatMsgRecordService.selectCountBySendName(u.getUsername(),user.getUsername());

            unreadMap.put(u.getUsername(),count);
        }
        mav.addObject("unreadMap",unreadMap);
        return mav;
    }
    @RequestMapping("/")
    public String toIndex(){
        return "redirect:/articleList";
    }
    @PostMapping("/ok/postUrl")
    public @ResponseBody User postUrl(Integer id){
        User user = new User();
        user.setNickName("123");
        user.setUsername("qwe");
        user.setPassword("zxc");
        return user;
    }
    @RequestMapping("/ok/toTetBase")
    public String toTetBase(){
        return "testBase";
    }
    @RequestMapping("/user/test")
    public void userTEst() {
        System.out.println("有权限");
    }
    @RequestMapping("/ok/isLogin")
    public @ResponseBody Boolean isLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o = authentication.getPrincipal();
        Boolean b = false;
        if (o instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) o;
            if (userDetails.getUsername() != null) {
                b = true;
            }
        }
        return b;
    }
    @RequestMapping(value="/ok/arr", method=RequestMethod.POST)
    public void arr(
            //HttpServletRequest request,
            //HttpServletResponse response
            @RequestBody RedisIO[] redisIOS
    ) throws IOException {
//        InputStream is = request.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len = is.read(bytes);
//        StringBuilder sb = new StringBuilder();
//        while (len > 0) {
//            sb.append(new String(bytes));
//            len = is.read(bytes);
//        }
//        System.out.println(sb.toString());
        System.out.println(redisIOS.length);
    }
    @RequestMapping("/ok/arrPost")
    public String arrPost(
            Integer id
    ) {
        System.out.println("run");
        return "arrPost";
    }
    @RequestMapping("/ok/path")
    public @ResponseBody Map<String, String> path() {
        Map<String, String> map = new HashMap<>();
        map.put("path", "https://www.processon.com/view/link/5ce4f82ee4b0c7d0f4cf7d24");
        return map;
    }
}
