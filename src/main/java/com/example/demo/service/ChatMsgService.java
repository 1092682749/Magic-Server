package com.example.demo.service;

import com.example.demo.model.ChatMsg;

import java.util.List;

public interface ChatMsgService {
    public List<ChatMsg> findByUser(Integer sendId, Integer reviceId);
    public int save(ChatMsg chatMsg);
}
