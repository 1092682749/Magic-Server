package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {
    public List<User> findAll();
}