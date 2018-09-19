package com.example.demo.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserame(String username) {
        this.username = username == null ? null : username.trim();
    }
    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj.getClass() != getClass()){
            return false;
        }
        User user = (User)obj;
        return id.equals(user.getId());
    }

}