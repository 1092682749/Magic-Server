package com.example.demo.service;

import com.example.demo.model.Friend;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendService {
    void insert(Friend friend);
    int addFriendRelate(Integer id1, Integer id2);
    List<User> findMyFriend() throws Exception;
    List<User> findMyAllFriend() throws Exception;
    List<User> findMyAllFriend(User user);
}
