package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class ASD {
    @Autowired
    UserService userService;
    @Transactional
    public void b() {
        User user = new User();
        user.setUsername("bbbbb");
        user.setNickName("bbbbbbbbb");
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
