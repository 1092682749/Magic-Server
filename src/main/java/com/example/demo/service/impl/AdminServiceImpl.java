package com.example.demo.service.impl;

import com.example.demo.dao.AdminMapper;
import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }
}
