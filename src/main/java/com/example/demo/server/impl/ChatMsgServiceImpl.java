package com.example.demo.server.impl;

import com.example.demo.dao.ChatMsgMapper;
import com.example.demo.model.ChatMsg;
import com.example.demo.server.ChatMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatMsgServiceImpl implements ChatMsgService {
    @Resource
    ChatMsgMapper chatMsgMapper;
    @Override
    public List<ChatMsg> findByUser(Integer sendId, Integer reviceId) {
        List<ChatMsg> chatMsgs = chatMsgMapper.selectBySendId(sendId,reviceId);
        return chatMsgs;
    }

    @Override
    @Transactional
    public int save(ChatMsg chatMsg)  {
//        if (true) {
//            throw new RuntimeException();
//        }
        return chatMsgMapper.insert(chatMsg);
    }
}
