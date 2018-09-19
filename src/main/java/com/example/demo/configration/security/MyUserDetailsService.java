package com.example.demo.configration.security;

import com.example.demo.model.SUser;
import com.example.demo.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //
        SUser user = new SUser();
        user.setUsername("123");
        user.setPassword("asd");
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails sUser = new SecurityUser(user,authorities);
        return sUser;
    }
}
