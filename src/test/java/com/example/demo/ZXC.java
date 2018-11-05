package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class ZXC {
    @Autowired
    UserService userService;
        @Transactional
        public void a() throws Exception {
//            throw new Exception();
            User user = new User();
            user.setNickName("a");
            user.setUsername("aaaaaaa");
            try {
                userService.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
