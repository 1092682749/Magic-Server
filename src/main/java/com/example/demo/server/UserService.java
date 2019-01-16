package com.example.demo.server;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    public int save(User user) throws Exception;
    public User findByUsername(String username) throws Exception;
    public List<User> findAll();
    User findById(Integer id);
}
