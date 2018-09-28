package com.example.demo.server.impl;

import com.example.demo.dao.ChatMsgMapper;
import com.example.demo.model.ChatMsg;
import com.example.demo.server.ChatMsgService;
import org.springframework.stereotype.Service;

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
    public int save(ChatMsg chatMsg) { return chatMsgMapper.insert(chatMsg); }
}
