package com.example.demo.server.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public User findByUsername(String username) throws Exception {
        List<User> userList = userMapper.findByUsername(username);
        if (userList.size() > 1) {
            throw new Exception("用户名不唯一");
        } else if (userList == null || userList.size() == 0) {
            throw new Exception("用户不存在");
        }
        return userList.get(0);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        return userList;
    }
}
