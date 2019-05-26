package com.example.demo.service.impl;

import com.example.demo.dao.FriendApplicationMapper;
import com.example.demo.model.FriendApplication;
import com.example.demo.service.FriendApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FriendApplicationServiceImpl implements FriendApplicationService {
    @Resource
    FriendApplicationMapper friendApplicationMapper;
    @Override
    public void insert(FriendApplication friendApplication) {
        friendApplicationMapper.insert(friendApplication);
    }

    @Override
    public void insertApplicationSingle(FriendApplication application) {
        friendApplicationMapper.insertApplicationSingle(application);
    }

    @Override
    public List<Map<String, Object>> findFriendApplication(Integer id) {
        return friendApplicationMapper.findFriendApplication(id);
    }

    @Override
    public int handleFriendApplication(FriendApplication friendApplication) {
        return friendApplicationMapper.handleFriendApplication(friendApplication);
    }
}
