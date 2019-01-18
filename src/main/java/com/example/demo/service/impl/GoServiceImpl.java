package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.GoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoServiceImpl implements GoService {

    @Autowired
    UserMapper userMapper;

    public void synMethod(Integer id){
        List<User> userList = userMapper.findAll();
        User synObject = userList.get(id);
        synchronized (synObject){
            String s = "s";
            synObject.setUsername(synObject.getUsername()+s);
        }
    }
}
