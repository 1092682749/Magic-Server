package com.example.demo.utils;



import com.example.demo.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConstantMap {
    public static  Map<String, Socket> ipMap = new HashMap<>();
//    public static User GetMyself() {
//        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userService.findByUsername(details.getUsername());
//    }
}
