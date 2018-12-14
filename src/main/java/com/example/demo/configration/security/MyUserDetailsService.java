package com.example.demo.configration.security;

import com.example.demo.model.SUser;
import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetailsService implements UserDetailsService {
    UserService userService;
    MyUserDetailsService(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //
        User user = null;
        try {
            user = userService.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        // 增加角色
        user.getUserRoleList().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRolename()));
        });
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        return securityUser;
    }
}
