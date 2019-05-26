package com.example.demo.dao;

import com.example.demo.model.Friend;
import com.example.demo.model.FriendExample;
import java.util.List;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    long countByExample(FriendExample example);

    int deleteByExample(FriendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByExample(FriendExample example);

    Friend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
    int addFriendRelate(@Param("id1")Integer id1, @Param("id2")Integer id2);
    List<Friend> findMyFriend(Integer id);
    List<User> findMyAllFriend(Integer id);
}
