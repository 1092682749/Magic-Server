package com.example.demo.server.impl;

import com.example.demo.dao.ChatMsgRecordMapper;
import com.example.demo.model.ChatMsg;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.server.ChatMsgRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatMsgRecordServiceImpl implements ChatMsgRecordService {
    @Resource
    ChatMsgRecordMapper chatMsgRecordMapper;
    @Override
    public List<ChatMsgRecord> findByUser(String sendName, String receiveName) {
        List<ChatMsgRecord> records = chatMsgRecordMapper.selectBySendName(sendName,receiveName);
        return records;
    }

    @Override
    @Transactional
    public int save(ChatMsgRecord chatMsgRecord) {
        int code = chatMsgRecordMapper.insert(chatMsgRecord);
//        if ("1".equals("2"))
//        throw new RuntimeException();
        return code;
    }

    @Override
    public Integer selectCountBySendName(String sendName, String receiveName) {
        return chatMsgRecordMapper.selectCountBySendName(sendName, receiveName);
    }

    @Override
    public void already(String sendName, String receiveName) {
        chatMsgRecordMapper.already(sendName, receiveName);
    }

    @Override
    public int deleteRecord(String sendname, String receivename) {
        return chatMsgRecordMapper.deleteRecord(sendname,receivename);
    }
}
