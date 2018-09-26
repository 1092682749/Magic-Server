package com.example.demo.server;

import com.example.demo.model.User;

public interface UserService {
    public int save(User user);
    public User findByUsername(String username) throws Exception;
}
