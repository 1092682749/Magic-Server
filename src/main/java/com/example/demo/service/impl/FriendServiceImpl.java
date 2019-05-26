package com.example.demo.service.impl;

import com.example.demo.dao.FriendMapper;
import com.example.demo.model.Friend;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    FriendMapper friendMapper;
    @Override
    public void insert(Friend friend) {
        friendMapper.insert(friend);
    }
}
