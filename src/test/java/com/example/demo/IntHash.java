package com.example.demo;

import com.example.demo.model.User;

public class IntHash {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("");
        Integer aa = null;
        Integer bb =null;
        System.out.println(aa.equals(bb));
        System.out.println(user.hashCode());
    }
}
