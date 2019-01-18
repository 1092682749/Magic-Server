package com.example.demo.service;

import com.example.demo.model.RedPacket;

public interface RedPacketService {

    public RedPacket getRedPacket(Integer id);

    public int decreaseRedPacket(Integer id);

    public int save(RedPacket redPacket);
}
