package com.example.demo.service;

import com.example.demo.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
}
