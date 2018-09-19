package com.example.demo.configration.security;

import com.example.demo.model.SUser;
import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    SUser user;
    String username;
    String password;
    Collection<SimpleGrantedAuthority> grantedAuthorityList;

    SecurityUser(SUser user, Collection<SimpleGrantedAuthority> grantedAuthorityList) {
        this.user = user;
        this.grantedAuthorityList = grantedAuthorityList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        this.password = user.getPassword();
        return password;
    }

    @Override
    public String getUsername() {
        this.username = user.getUsername();
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
