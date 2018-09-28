package com.example.demo.server;

import com.example.demo.model.ChatMsg;
import com.example.demo.model.ChatMsgRecord;

import java.util.List;

public interface ChatMsgRecordService {
    public List<ChatMsgRecord> findByUser(String sendName, String receiveName);
    public int save(ChatMsgRecord chatMsgRecord);
    public Integer selectCountBySendName(String sendName, String receiveName);
    void already(String sendName, String receiveName);
}
