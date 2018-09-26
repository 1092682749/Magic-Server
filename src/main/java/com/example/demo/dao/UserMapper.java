package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
//    long countByExample(UserExample example);
//
//    int deleteByExample(UserExample example);
//
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(User record);
//
//    int insertSelective(User record);
//
//    List<User> selectByExample(UserExample example);
//
//    User selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);
//
//    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
    List<User> findAll();

    int save(User user);

    List<User> findByUsername(String username);
}