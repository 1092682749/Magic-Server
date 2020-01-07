package com.example.demo.controller;

import com.example.demo.model.Friend;
import com.example.demo.model.FriendApplication;
import com.example.demo.model.User;
import com.example.demo.service.FriendApplicationService;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ok")
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;
    @Autowired
    FriendApplicationService friendApplicationService;
    @RequestMapping("/addfriend")
    public @ResponseBody ResponseResult addFriend(HttpServletRequest request) throws Exception {
        String destinationName = request.getParameter("destinationName");
        String applicationName = request.getParameter("applicationName");
        User user = userService.findByUsername(destinationName);
        Map<String, String> msg = new HashMap<>();

        if (user != null) { // 查询是否存在该用户

            FriendApplication friendApplication = new FriendApplication();
            User currentUser = null;
            if (applicationName == null) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                currentUser = userService.findByUsername(userDetails.getUsername());
            } else {
                currentUser = userService.findByUsername(applicationName);
            }

            friendApplication.setApplicationId(currentUser.getId());
            friendApplication.setDestinationId(user.getId());
            friendApplication.setState(0);
            friendApplicationService.insertApplicationSingle(friendApplication);
            msg.put("msg", "申请成功!");
        } else {
            msg.put("msg", "申请失败!");
        }
        return new ResponseResult(msg);
    }

    @RequestMapping("/handleFriendApplication")
    public @ResponseBody ResponseResult handleFriendApplication(@RequestBody FriendApplication friendApplication) {
        int res = friendApplicationService.handleFriendApplication(friendApplication);
        ResponseResult responseResult = new ResponseResult("error");
        int res1 = friendService.addFriendRelate(friendApplication.getApplicationId(), friendApplication.getDestinationId());
        if (res > 0 && res1 > 0) {
            responseResult.setMessage("ok");
        }
        return responseResult;
    }
    @RequestMapping("/getFriendApplication")
    public @ResponseBody List<Map<String, Object>> getFriendApplication(HttpServletRequest request) throws Exception {
        String username = (String) request.getParameter("username");
        System.out.println(username);
        User user = null;

        if (username != null) {
            user = userService.findByUsername(username);
        }
        if (user == null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userService.findByUsername(userDetails.getUsername());
        }

        List<Map<String, Object>> applicationList = friendApplicationService.findFriendApplication(user.getId());
        return applicationList;
    }
}
