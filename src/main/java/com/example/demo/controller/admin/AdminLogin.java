package com.example.demo.controller.admin;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminLogin {
    @Autowired
    AdminService adminService;
    @RequestMapping("/admin/login")
    public @ResponseBody Boolean login(@RequestBody Admin admin) {
        if (adminService.findByUsername(admin.getUsername()) != null) {
            return true;
        }
        return false;
    }
}
