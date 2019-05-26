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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;
    @Autowired
    FriendApplicationService friendApplicationService;
    @RequestMapping("/addfriend")
    public @ResponseBody ResponseResult addFriend(String destinationName) throws Exception {

        User user = userService.findByUsername(destinationName);
        Map<String, String> msg = new HashMap<>();

        if (user != null) { // 查询是否存在该用户

            FriendApplication friendApplication = new FriendApplication();
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User currentUser = userService.findByUsername(userDetails.getUsername());
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
}
