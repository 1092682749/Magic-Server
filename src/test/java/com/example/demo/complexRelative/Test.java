package com.example.demo.complexRelative;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.RoleMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RoleList;
import java.util.List;

public class Test extends DemoApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @org.junit.Test
    public void test() {
        Role role = roleMapper.findRoleById(1);
//        List<RolePermission> rolePermissionList = role.getRolePermissionList();
        Role role1 = roleMapper.findRoleById(1);
//        Permission permission = rolePermissionList.get(0).getPermission();
//        System.out.println(permission.getPermission());
        List<User> userList = userMapper.findByUsername("123");
        System.out.println(userList.get(0).getUsername());
        userList.forEach(user -> {
            List<UserRole> userRoleList = user.getUserRoleList();
            System.out.println(userRoleList.get(0).getRole().getRolename());
        });
    }
}
