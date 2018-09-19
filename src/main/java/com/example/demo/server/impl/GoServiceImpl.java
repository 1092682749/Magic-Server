package com.example.demo.server.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.server.GoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
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
            synObject.setUserame(synObject.getUsername()+s);
        }
    }
}
