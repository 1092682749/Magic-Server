package com.example.demo.service;

import com.example.demo.model.FriendApplication;

import java.util.List;
import java.util.Map;

public interface FriendApplicationService {
    public void insert(FriendApplication friendApplication);
    void insertApplicationSingle(FriendApplication application);
    List<Map<String, Object>> findFriendApplication(Integer id);
    int handleFriendApplication(FriendApplication friendApplication);
}
