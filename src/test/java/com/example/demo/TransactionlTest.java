package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionlTest {
    @Autowired
    UserService userService;
    @Autowired
    ASD asd;
    @Autowired
    ZXC zxc;
    @Test
    @Transactional
    public void main() throws Exception {
        asd.b();
        zxc.a();
    }


}


