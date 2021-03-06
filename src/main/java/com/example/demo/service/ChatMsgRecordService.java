package com.example.demo.service;

import com.example.demo.model.ChatMsgRecord;

import java.util.List;

public interface ChatMsgRecordService {
    public List<ChatMsgRecord> findByUser(String sendName, String receiveName);
    public int save(ChatMsgRecord chatMsgRecord);
    public Integer selectCountBySendName(String sendName, String receiveName);
    void already(String sendName, String receiveName);
    int deleteRecord(String sendname,String receivename);
}
