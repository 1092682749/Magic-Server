package com.example.demo.server.impl;

import com.example.demo.dao.RedPacketMapper;
import com.example.demo.model.RedPacket;
import com.example.demo.server.RedPacketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Resource
    private RedPacketMapper redPacketMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Integer id) {
        return redPacketMapper.getRedPacket(id);
    }

    @Override
    public int decreaseRedPacket(Integer id) {
        return redPacketMapper.decreaseRedPacket(id);
    }
}
