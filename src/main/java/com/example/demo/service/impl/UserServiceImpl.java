package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public int save(User user) throws Exception {
        return userMapper.save(user);
    }

    @Override
    public User findByUsername(String username) throws Exception {
        List<User> userList = userMapper.findByUsername(username);
        if (userList.size() > 1) {
            throw new Exception("用户名不唯一");
        } else if (userList == null || userList.size() == 0) {
            return null ;
        }
        return userList.get(0);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        return userList;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
